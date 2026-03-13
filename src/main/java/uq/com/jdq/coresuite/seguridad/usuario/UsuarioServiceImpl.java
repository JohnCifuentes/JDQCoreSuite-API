package uq.com.jdq.coresuite.seguridad.usuario;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacion;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacionRepository;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.config.exceptions.RegistroRepetidoException;
import uq.com.jdq.coresuite.infra.autenticationevents.AuthenticationEventsDTO;
import uq.com.jdq.coresuite.infra.autenticationevents.AuthenticationEventsService;
import uq.com.jdq.coresuite.infra.authenticationeventstype.AuthenticationEventsTypeService;
import uq.com.jdq.coresuite.notificacion.EmailDTO;
import uq.com.jdq.coresuite.notificacion.NotificacionService;
import uq.com.jdq.coresuite.seguridad.rolusuario.RolUsuarioService;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio encargado de administrar usuarios y credenciales.
 */
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final EmpresaRepository empresaRepository;
    private final TipoIdentificacionRepository tipoIdentificacionRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationEventsService authenticationEventsService;
    private final AuthenticationEventsTypeService authenticationEventsTypeService;
    private final NotificacionService notificacionService;
    private final RolUsuarioService rolUsuarioService;

    /**
     * Registra un nuevo usuario validando empresa, tipo de identificacion y unicidad del correo.
     * @param createUsuarioDTO datos del usuario a crear.
     * @return usuario creado.
     * @throws Exception si existen inconsistencias de negocio.
     */
    @Override
    @Transactional
    public ResponseUsuarioDTO createUsuario(CreateUsuarioDTO createUsuarioDTO) throws Exception {
        Usuario usuario = usuarioMapper.toEntity(createUsuarioDTO);
        /**
         *
         */
        Optional<Empresa> empresa = empresaRepository.findById(createUsuarioDTO.empresaId());
        if(empresa.isEmpty()) {
            throw new NoExisteException("No existe la empresa");
        }
        /**
         *
         */
        Optional<TipoIdentificacion> tipoIdentificacion = tipoIdentificacionRepository.findById(createUsuarioDTO.tipoIdentificacionId());
        if(tipoIdentificacion.isEmpty()) {
            throw new NoExisteException("No existe un tipo de identificacion");
        }
        /**
         *
         */
        if(this.getUsuarioByCorreoElectronico(createUsuarioDTO.correoElectronico()).isPresent()){
            throw new RegistroRepetidoException("El correo electronico ya existe");
        }
        /**
         *
         */
        usuario.setEmpresa(empresa.get());
        usuario.setTipoIdentificacion(tipoIdentificacion.get());
        usuario.setPassword(passwordEncoder.encode(createUsuarioDTO.numeroIdentificacion()));
        usuario = usuarioRepository.save(usuario);
        notificacionService.enviarNotificacion(getEmailDTO(usuario));
        return usuarioMapper.toDTO(usuario);
    }

    /**
     * Actualiza un usuario validando dependencias y reglas de unicidad.
     * @param id identificador del usuario.
     * @param updateUsuarioDTO nuevos datos del usuario.
     * @return usuario actualizado.
     * @throws Exception si existen inconsistencias de negocio.
     */
    @Override
    @Transactional
    public ResponseUsuarioDTO updateUsuario(Long id, UpdateUsuarioDTO updateUsuarioDTO) throws Exception {
        Optional<Empresa> empresa = empresaRepository.findById(updateUsuarioDTO.empresaId());
        if(empresa.isEmpty()) {
            throw new NoExisteException("No existe la empresa");
        }
        /**
         *
         */
        Optional<TipoIdentificacion> tipoIdentificacion = tipoIdentificacionRepository.findById(updateUsuarioDTO.tipoIdentificacionId());
        if(tipoIdentificacion.isEmpty()) {
            throw new NoExisteException("No existe un tipo de identificacion");
        }
        /**
         *
         */
        if(this.getUsuarioByCorreoElectronico(updateUsuarioDTO.correoElectronico()).isPresent()){
            throw new RegistroRepetidoException("El correo electronico ya existe");
        }
        /**
         *
         */
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()) {
            throw new NoExisteException("No existe un usuario");
        }
        Usuario usuarioAux = usuario.get();
        usuarioMapper.updateEntityFromDTO(updateUsuarioDTO, usuarioAux);
        usuarioAux.setEmpresa(empresa.get());
        usuarioAux.setTipoIdentificacion(tipoIdentificacion.get());
        usuarioAux = usuarioRepository.save(usuarioAux);
        return usuarioMapper.toDTO(usuarioAux);
    }

    /**
     * Cambia el estado de un usuario existente.
     * @param id identificador del usuario.
     * @param inactiveUsuarioDTO datos del nuevo estado.
     * @return usuario actualizado.
     */
    @Override
    @Transactional
    public ResponseUsuarioDTO inactiveUsuario(Long id, InactiveUsuarioDTO inactiveUsuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioMapper.inactiveEntityFromDTO(inactiveUsuarioDTO, usuario);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    /**
     * Obtiene todos los usuarios registrados.
     * @return lista de usuarios.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseUsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Consulta un usuario por su identificador.
     * @param id identificador del usuario.
     * @return usuario encontrado.
     * @throws Exception si el usuario no existe.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseUsuarioDTO getUsuarioById(Long id) throws Exception{
        Optional<Usuario> usuariobd = usuarioRepository.findById(id);
        if(usuariobd.isEmpty()){
            throw new NoExisteException("No existe el usuario");
        }
        return usuarioMapper.toDTO(usuariobd.get());
    }

    /**
     * Obtiene los usuarios asociados a una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de usuarios.
     * @throws Exception si la empresa no existe.
     */
    @Override
    public List<ResponseUsuarioDTO> getUsuariosByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() ->
                new NoExisteException("No existe la empresa")
        );
        return usuarioRepository.findByEmpresa(empresa);
    }

    /**
     * Valida un usuario a partir de su correo electronico y contrasena.
     * @param usuarioCredencialesDTO credenciales de acceso.
     * @return entidad del usuario autenticado.
     * @throws Exception si el usuario no existe o las credenciales son invalidas.
     */
    @Override
    public Usuario getUsuarioByCorreoElectronicoAndPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception {
        Optional<Usuario> usuario = this.getUsuarioByCorreoElectronico(usuarioCredencialesDTO.correoElectronico());
        if(usuario.isEmpty()){
            AuthenticationEventsDTO authenticationEventsDTO = new AuthenticationEventsDTO(
                usuarioCredencialesDTO.correoElectronico(),
                authenticationEventsTypeService.getAuthenticationEventsTypeById(2),
                "UsuarioServiceImpl.getUsuarioByCorreoElectronicoAndPassword"
            );
            authenticationEventsService.createAuthenticationEvent(authenticationEventsDTO);
            throw new NoExisteException("No existe el correo electronico");
        }
        if(!passwordEncoder.matches(usuarioCredencialesDTO.password(), usuario.get().getPassword())){
            AuthenticationEventsDTO authenticationEventsDTO = new AuthenticationEventsDTO(
                    usuarioCredencialesDTO.correoElectronico(),
                    authenticationEventsTypeService.getAuthenticationEventsTypeById(3),
                    "UsuarioServiceImpl.getUsuarioByCorreoElectronicoAndPassword"
            );
            authenticationEventsService.createAuthenticationEvent(authenticationEventsDTO);
            throw new BadCredentialsException("Credenciales incorrectas");
        }
        return usuario.get();
    }

    /**
     * Restablece la contrasena de un usuario y marca que ya no es su primer acceso.
     * @param usuarioCredencialesDTO correo y nueva contrasena.
     * @return usuario actualizado.
     * @throws Exception si el usuario no existe.
     */
    @Override
    public ResponseUsuarioDTO recuperarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception {
        Optional<Usuario> usuario = this.getUsuarioByCorreoElectronico(usuarioCredencialesDTO.correoElectronico());
        if(usuario.isPresent()){
            Usuario usuarioAux = usuario.get();
            usuarioAux.setPassword(passwordEncoder.encode(usuarioCredencialesDTO.password()));
            usuarioAux.setPrimerAcceso(false);
            usuarioRepository.save(usuarioAux);
            return usuarioMapper.toDTO(usuarioAux);
        } else {
            throw new NoExisteException("No existe el usuario");
        }
    }

    /**
     * Actualiza la contrasena de un usuario existente.
     * @param usuarioCredencialesDTO correo y nueva contrasena.
     * @return usuario actualizado.
     * @throws Exception si el usuario no existe.
     */
    @Override
    public ResponseUsuarioDTO actualizarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception {
        Optional<Usuario> usuario = this.getUsuarioByCorreoElectronico(usuarioCredencialesDTO.correoElectronico());
        if(usuario.isPresent()){
            Usuario usuarioAux = usuario.get();
            usuarioAux.setPassword(passwordEncoder.encode(usuarioCredencialesDTO.password()));
            usuarioRepository.save(usuarioAux);
            return usuarioMapper.toDTO(usuarioAux);
        } else {
            throw new NoExisteException("No existe el usuario");
        }
    }

    /**
     * Bloquea un usuario, registra el evento y notifica a la empresa.
     * @param correoElectronico correo electronico del usuario.
     * @return usuario bloqueado.
     * @throws Exception si el usuario no existe.
     */
    @Override
    public ResponseUsuarioDTO blockUsuario(String correoElectronico) throws Exception {
        Optional<Usuario> usuario = this.getUsuarioByCorreoElectronico(correoElectronico);
        if(usuario.isPresent()){
            Usuario usuarioAux = usuario.get();
            usuarioAux.setEstado("B");
            usuarioRepository.save(usuarioAux);
            AuthenticationEventsDTO authenticationEventsDTO = new AuthenticationEventsDTO(
                    usuarioAux.getCorreoElectronico(),
                    authenticationEventsTypeService.getAuthenticationEventsTypeById(5),
                    "LoginServiceImp.login"
            );
            authenticationEventsService.createAuthenticationEvent(authenticationEventsDTO);
            notificacionService.enviarNotificacion(getEmailDTO(usuarioAux));
            return usuarioMapper.toDTO(usuarioAux);
        } else {
            throw new NoExisteException("No existe el usuario");
        }
    }

    /**
     * Desbloquea un usuario y restablece su estado de primer acceso.
     * @param usuarioId identificador del usuario.
     * @return usuario desbloqueado.
     * @throws Exception si el usuario no existe.
     */
    @Override
    public ResponseUsuarioDTO unblockUsuario(Long usuarioId) throws Exception{
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->  new NoExisteException("No existe un usuario"));
        usuario.setEstado("A");
        usuario.setPrimerAcceso(true);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    /**
     * Busca un usuario por correo electronico.
     * @param correoElectronico correo a consultar.
     * @return usuario encontrado, si existe.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public Optional<Usuario> getUsuarioByCorreoElectronico(String correoElectronico) throws Exception {
        return usuarioRepository.findByCorreoElectronico(correoElectronico);
    }

    /**
     * Consulta una entidad de usuario por identificador.
     * @param usuarioId identificador del usuario.
     * @return entidad del usuario.
     * @throws Exception si el usuario no existe.
     */
    @Override
    public Usuario getById(Long usuarioId) throws Exception{
        Optional<Usuario> usuariobd = usuarioRepository.findById(usuarioId);
        if(usuariobd.isEmpty()){
            throw new NoExisteException("No existe el usuario");
        }
        return usuariobd.get();
    }

    /**
     * Construye el correo de bienvenida con las credenciales iniciales del usuario.
     * @param usuario usuario destinatario.
     * @return mensaje listo para enviar.
     */
    private static @NotNull EmailDTO getEmailDTO(Usuario usuario) {
        String cuerpo = """
        Hola %s,
        
        Â¡Bienvenido a JDQ - CoreSuite!
        
        Su usuario ha sido registrado exitosamente en nuestra plataforma.
        
        A continuaciÃ³n encontrarÃ¡ sus credenciales de acceso inicial:
        
        Usuario: %s
        ContraseÃ±a: %s
        
        Puede acceder al sistema desde el siguiente enlace:
        https://jdq-coresuite-app.web.app/
        
        Por razones de seguridad, le recomendamos cambiar su contraseÃ±a despuÃ©s de iniciar sesiÃ³n por primera vez.
 
        Atentamente,
        Equipo JDQ - CoreSuite
        """.formatted(
                (usuario.getNombre1() + ' ' + usuario.getApellido1()),
                usuario.getCorreoElectronico(),
                usuario.getNumeroIdentificacion()
        );
        EmailDTO emailDTO = new EmailDTO("Bienvenido a JDQ - CoreSuite", cuerpo, usuario.getCorreoElectronico());
        return emailDTO;
    }

    /**
     * Construye el correo de notificacion para informar el bloqueo de una cuenta.
     * @param usuario usuario bloqueado.
     * @return mensaje listo para enviar.
     */
    private static @NotNull EmailDTO getEmailDTOBlock(Usuario usuario) {
        String cuerpo = """
            Se ha recibido una solicitud de desbloqueo de cuenta en JDQ - CoreSuite.

            El usuario ha sido bloqueado debido a mÃºltiples intentos fallidos de inicio de sesiÃ³n.

            InformaciÃ³n del usuario:

            Tipo de identificaciÃ³n: %s
            NÃºmero de identificaciÃ³n: %s
            Nombre: %s
            Correo electrÃ³nico: %s

            Por favor verifique la informaciÃ³n y, si corresponde, proceda con el desbloqueo de la cuenta desde el panel administrativo.

            Este mensaje fue generado automÃ¡ticamente por el sistema.

            JDQ - CoreSuite
            """.formatted(
                usuario.getTipoIdentificacion().getCodigo(),
                usuario.getNumeroIdentificacion(),
                usuario.getNombre1() + ' ' + usuario.getApellido1(),
                usuario.getCorreoElectronico()
        );
        EmailDTO emailDTO = new EmailDTO("Acceso bloqueado", cuerpo, usuario.getEmpresa().getCorreoElectronico());
        return emailDTO;
    }

}

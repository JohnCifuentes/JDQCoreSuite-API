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
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        usuario = usuarioRepository.save(usuario);
        notificacionService.enviarNotificacion(getEmailDTO(usuario));
        return usuarioMapper.toDTO(usuario);
    }

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

    @Override
    @Transactional
    public ResponseUsuarioDTO inactiveUsuario(Long id, InactiveUsuarioDTO inactiveUsuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioMapper.inactiveEntityFromDTO(inactiveUsuarioDTO, usuario);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseUsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseUsuarioDTO getUsuarioById(Long id) {
        Optional<Usuario> usuariobd = usuarioRepository.findById(id);
        ResponseUsuarioDTO responseUsuarioDTO = usuarioMapper.toDTO(usuariobd.get());
        return responseUsuarioDTO;
    }

    @Override
    public List<ResponseUsuarioDTO> getUsuariosByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() ->
                new NoExisteException("No existe la empresa")
        );
        return usuarioRepository.findByEmpresa(empresa);
    }

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

    @Override
    public ResponseUsuarioDTO recuperarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception {
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

            return usuarioMapper.toDTO(usuarioAux);
        } else {
            throw new NoExisteException("No existe el usuario");
        }
    }

    @Override
    public Optional<Usuario> getUsuarioByCorreoElectronico(String correoElectronico) throws Exception {
        return usuarioRepository.findByCorreoElectronico(correoElectronico);
    }

    private static @NotNull EmailDTO getEmailDTO(Usuario usuario) {
        String cuerpo = """
        Hola %s,
        
        ¡Bienvenido a JDQ - CoreSuite!
        
        Su usuario ha sido registrado exitosamente en nuestra plataforma.
        
        A continuación encontrará sus credenciales de acceso inicial:
        
        Usuario: %s
        Contraseña: %s
        
        Puede acceder al sistema desde el siguiente enlace:
        https://app.jdq.com
        
        Por razones de seguridad, le recomendamos cambiar su contraseña después de iniciar sesión por primera vez.
 
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

}

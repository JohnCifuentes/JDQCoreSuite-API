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

    /**
     * Registra un nuevo usuario validando empresa, tipo de identificacion y unicidad del correo.
     * @param createUsuarioDTO datos del usuario a crear.
     * @return usuario creado.
     * @throws Exception si existen inconsistencias de negocio.
     */
    @Override
    @Transactional
    public ResponseUsuarioDTO createUsuario(CreateUsuarioDTO createUsuarioDTO) throws Exception {
        existeCorreoElectronico(createUsuarioDTO.correoElectronico());
        Usuario usuario = usuarioMapper.toEntity(createUsuarioDTO);
        usuario.setEmpresa(getEmpresa(createUsuarioDTO.empresaId()));
        usuario.setTipoIdentificacion(getTipoIdentificacion(createUsuarioDTO.tipoIdentificacionId()));
        usuario.setPassword(passwordEncoder.encode(createUsuarioDTO.numeroIdentificacion()));
        usuario = usuarioRepository.save(usuario);
        notificacionService.enviarNotificacion(getWelcomeEmailDTO(usuario));
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
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()) {
            throw new NoExisteException("No existe un usuario");
        }
        Usuario usuarioAux = usuario.get();
        usuarioMapper.updateEntityFromDTO(updateUsuarioDTO, usuarioAux);
        usuarioAux.setEmpresa(getEmpresa(updateUsuarioDTO.empresaId()));
        usuarioAux.setTipoIdentificacion(getTipoIdentificacion(updateUsuarioDTO.tipoIdentificacionId()));
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
            notificacionService.enviarNotificacion(getBlockEmailDTO(usuarioAux));
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
    private @NotNull EmailDTO getWelcomeEmailDTO(Usuario usuario) {
        String cuerpo = """
		<!DOCTYPE html>
		<html>
		<head>
		  <meta charset="UTF-8">
		</head>
		<body style="margin:0; padding:0; background-color:#f4f6f8; font-family: Arial, sans-serif;">
		  <table width="100%%" cellpadding="0" cellspacing="0" style="background-color:#f4f6f8; padding:20px 0;">
			<tr>
			  <td align="center">
				<table width="600" cellpadding="0" cellspacing="0" style="background-color:#ffffff; border-radius:8px; overflow:hidden;">
				  <!-- Header -->
				  <tr>
					<td style="background-color:#1f3c88; padding:20px; text-align:center; color:white; font-size:20px; font-weight:bold;">
					  JDQ - CoreSuite
					</td>
				  </tr>
				  <!-- Body -->
				  <tr>
					<td style="padding:30px; color:#333333; font-size:14px; line-height:1.6;">
					  <p>Hola <strong>%s</strong>,</p>
					  <p>
						¡Bienvenido a <strong>JDQ - CoreSuite</strong>!
					  </p>
					  <p>
						Su usuario ha sido registrado exitosamente en nuestra plataforma.
					  </p>
					  <!-- Credenciales -->
					  <table width="100%%" cellpadding="0" cellspacing="0" style="background-color:#f1f5fb; border-radius:6px; margin:20px 0; padding:15px;">
						<tr>
						  <td style="font-size:14px; color:#333;">
							<strong>Usuario:</strong><br>
							%s
						  </td>
						</tr>
						<tr>
						  <td style="padding-top:10px; font-size:14px; color:#333;">
							<strong>Contraseña:</strong><br>
							%s
						  </td>
						</tr>
					  </table>
					  <p style="text-align:center; margin:30px 0;">
						<a href="https://jdq-coresuite-app.web.app/" 
						   style="background-color:#1f3c88; color:#ffffff; padding:12px 20px; text-decoration:none; border-radius:5px; display:inline-block;">
						   Acceder al sistema
						</a>
					  </p>
					  <!-- Seguridad -->
					  <p style="background-color:#fff4e5; padding:12px; border-radius:6px; font-size:13px; color:#8a6d3b;">
						Por razones de seguridad, le recomendamos cambiar su contraseña después de iniciar sesión por primera vez.
					  </p>
					  <p>
						Atentamente,<br>
						<strong>Equipo JDQ - CoreSuite</strong>
					  </p>
					</td>
				  </tr>
				  <!-- Footer -->
				  <tr>
					<td style="background-color:#f1f1f1; text-align:center; padding:15px; font-size:12px; color:#777;">
					  © 2026 JDQ - CoreSuite. Todos los derechos reservados.
					</td>
				  </tr>
				</table>
			  </td>
			</tr>
		  </table>
		</body>
		</html>
		""".formatted(
            (usuario.getNombre1() + " " + usuario.getApellido1()),
            usuario.getCorreoElectronico(),
            usuario.getNumeroIdentificacion()
        );
        return new EmailDTO("Bienvenido a JDQ - CoreSuite", cuerpo, usuario.getCorreoElectronico());
    }

    /**
     * Construye el correo de notificacion para informar el bloqueo de una cuenta.
     * @param usuario usuario bloqueado.
     * @return mensaje listo para enviar.
     */
    private @NotNull EmailDTO getBlockEmailDTO(Usuario usuario) {
        String cuerpo = """
        <!DOCTYPE html>
        <html>
        <head>
          <meta charset="UTF-8">
        </head>
        <body style="margin:0; padding:0; background-color:#f4f6f8; font-family: Arial, sans-serif;">
          <table width="100%%" cellpadding="0" cellspacing="0" style="background-color:#f4f6f8; padding:20px 0;">
            <tr>
              <td align="center">
                <table width="600" cellpadding="0" cellspacing="0" style="background-color:#ffffff; border-radius:8px; overflow:hidden;">
                  <tr>
                    <td style="background-color:#1f3c88; padding:20px; text-align:center; color:white; font-size:20px; font-weight:bold;">
                      JDQ - CoreSuite
                    </td>
                  </tr>
                  <tr>
                    <td style="padding:30px; color:#333333; font-size:14px; line-height:1.6;">
                      <p><strong>Notificación de cuenta bloqueada</strong></p>
                      <p>Le informamos que un usuario ha sido bloqueado en <strong>JDQ - CoreSuite</strong> debido a múltiples intentos fallidos de inicio de sesión.</p>
                      <table width="100%%" cellpadding="0" cellspacing="0" style="background-color:#f1f5fb; border-radius:6px; margin:20px 0; padding:15px;">
                        <tr>
                          <td style="font-size:14px; color:#333;">
                            <strong>Tipo de identificación:</strong><br>
                            %s
                          </td>
                        </tr>
                        <tr>
                          <td style="padding-top:10px; font-size:14px; color:#333;">
                            <strong>Número de identificación:</strong><br>
                            %s
                          </td>
                        </tr>
                        <tr>
                          <td style="padding-top:10px; font-size:14px; color:#333;">
                            <strong>Nombre:</strong><br>
                            %s
                          </td>
                        </tr>
                        <tr>
                          <td style="padding-top:10px; font-size:14px; color:#333;">
                            <strong>Correo electrónico:</strong><br>
                            %s
                          </td>
                        </tr>
                      </table>
                      <p style="background-color:#fdecea; padding:12px; border-radius:6px; font-size:13px; color:#a94442;">
                        Este bloqueo se realizó automáticamente como medida de seguridad.
                      </p>
                      <p style="font-size:12px; color:#777;">
                        Este mensaje es únicamente informativo. La gestión del desbloqueo debe realizarse a través de los canales administrativos correspondientes.
                      </p>
                      <p>
                        Atentamente,<br>
                        <strong>JDQ - CoreSuite</strong>
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td style="background-color:#f1f1f1; text-align:center; padding:15px; font-size:12px; color:#777;">
                      © 2026 JDQ - CoreSuite. Todos los derechos reservados.
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </body>
        </html>
        """.formatted(
            usuario.getTipoIdentificacion().getCodigo(),
            usuario.getNumeroIdentificacion(),
            usuario.getNombre1() + " " + usuario.getApellido1(),
            usuario.getCorreoElectronico()
        );
        return new EmailDTO("Acceso bloqueado", cuerpo, usuario.getEmpresa().getCorreoElectronico());
    }

    /**
     * Valida que la empresa exista
     * @param empresaId Consecutivo de la empresa
     * @return Empresa
     * @throws Exception si ocurre un error de negocio.
     */
    private Empresa getEmpresa(Long empresaId) throws Exception {
        Optional<Empresa> empresa = empresaRepository.findById(empresaId);
        if(empresa.isEmpty()) {
            throw new NoExisteException("No existe la empresa");
        }
        return empresa.get();
    }

    /**
     * Valida si el tipo de identificacion exista
     * @param tipoIdentificacionId Consecutivo del tipo de identifcacion
     * @return TipoIdentificacion
     * @throws Exception si ocurre un error de negocio.
     */
    private TipoIdentificacion getTipoIdentificacion(Long tipoIdentificacionId) throws Exception {
        Optional<TipoIdentificacion> tipoIdentificacion = tipoIdentificacionRepository.findById(tipoIdentificacionId);
        if(tipoIdentificacion.isEmpty()) {
            throw new NoExisteException("No existe un tipo de identificacion");
        }
        return tipoIdentificacion.get();
    }

    /**
     * Valida si ya existe un correo electrónico
     * @param correoElectronico correoElectronico
     * @throws Exception si ocurre un error de negocio.
     */
    private void existeCorreoElectronico(String correoElectronico) throws Exception {
        if(this.getUsuarioByCorreoElectronico(correoElectronico).isPresent()){
            throw new RegistroRepetidoException("El correo electronico ya existe");
        }
    }

}

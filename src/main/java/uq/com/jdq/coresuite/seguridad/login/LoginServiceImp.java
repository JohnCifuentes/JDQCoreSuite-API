package uq.com.jdq.coresuite.seguridad.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uq.com.jdq.coresuite.config.JWTUtils;
import uq.com.jdq.coresuite.config.TokenDTO;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.infra.autenticationevents.AuthenticationEventsDTO;
import uq.com.jdq.coresuite.infra.autenticationevents.AuthenticationEventsService;
import uq.com.jdq.coresuite.infra.authenticationeventstype.AuthenticationEventsTypeService;
import uq.com.jdq.coresuite.seguridad.rolusuario.ResponseRolUsuarioDTO;
import uq.com.jdq.coresuite.seguridad.rolusuario.RolUsuarioService;
import uq.com.jdq.coresuite.seguridad.usuario.ResponseUsuarioDTO;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.seguridad.usuario.UsuarioCredencialesDTO;
import uq.com.jdq.coresuite.seguridad.usuario.UsuarioService;
import uq.com.jdq.coresuite.sistema.licencia.LicenciaService;
import uq.com.jdq.coresuite.sistema.licencia.ResponseLicenciaDTO;
import uq.com.jdq.coresuite.sistema.sesion.CreateSesionDTO;
import uq.com.jdq.coresuite.sistema.sesion.ResponseSesionDTO;
import uq.com.jdq.coresuite.sistema.sesion.SesionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementacion del servicio de autenticacion y cierre de sesion.
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImp implements LoginService {
    private final UsuarioService usuarioService;
    private final SesionService sesionService;
    private final LicenciaService licenciaService;
    private final JWTUtils jwtUtils;
    private final AuthenticationEventsService authenticationEventsService;
    private final AuthenticationEventsTypeService authenticationEventsTypeService;
    private final RolUsuarioService rolUsuarioService;

    /**
     * Autentica un usuario, valida restricciones operativas y genera su token.
     * @param loginDTO credenciales de autenticacion.
     * @return token generado para la sesion.
     * @throws Exception si ocurre un error durante la autenticacion.
     */
    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception{
        UsuarioCredencialesDTO usuarioCredencialesDTO = new UsuarioCredencialesDTO(loginDTO.correoElectronico(), loginDTO.password());
        Usuario usuario = this.usuarioService.getUsuarioByCorreoElectronicoAndPassword(usuarioCredencialesDTO);
        if(!(usuario.apellido1.equals("ADMIN") || usuario.apellido2.equals("SUPER/ADMIN"))){
            /**
             *
             */
            if(usuario.getEstado().equals("B")){
                AuthenticationEventsDTO authenticationEventsDTO = new AuthenticationEventsDTO(
                        usuarioCredencialesDTO.correoElectronico(),
                        authenticationEventsTypeService.getAuthenticationEventsTypeById(9),
                        "LoginServiceImp.login"
                );
                authenticationEventsService.createAuthenticationEvent(authenticationEventsDTO);
                throw new NoExisteException("El usuario se encuentra bloqueado.");
            }
            /**
             *
             */
            if(usuario.getEstado().equals("I")){
                AuthenticationEventsDTO authenticationEventsDTO = new AuthenticationEventsDTO(
                        usuarioCredencialesDTO.correoElectronico(),
                        authenticationEventsTypeService.getAuthenticationEventsTypeById(10),
                        "LoginServiceImp.login"
                );
                authenticationEventsService.createAuthenticationEvent(authenticationEventsDTO);
                throw new NoExisteException("El usuario se encuentra inactivo.");
            }
            /**
             *
             */
            List<ResponseLicenciaDTO> licencias = licenciaService.getLicenciasByEmpresa(usuario.getEmpresa().getId());
            ResponseLicenciaDTO responseLicenciaDTO = licencias
                    .stream()
                    .filter(ResponseLicenciaDTO::activo)
                    .findFirst()
                    .orElse(null);
            if (responseLicenciaDTO == null || !responseLicenciaDTO.activo() || !responseLicenciaDTO.estado().equals("A")) {
                AuthenticationEventsDTO authenticationEventsDTO = new AuthenticationEventsDTO(
                        usuarioCredencialesDTO.correoElectronico(),
                        authenticationEventsTypeService.getAuthenticationEventsTypeById(4),
                        "LoginServiceImp.login"
                );
                authenticationEventsService.createAuthenticationEvent(authenticationEventsDTO);
                throw new NoExisteException("No existe una licencia vigente para esta empresa.");
            }
            /**
             *
             */
            List<ResponseSesionDTO> sesiones = sesionService.getSesionesByEmpresa(usuario.getEmpresa().getId());
            sesiones = sesiones
                    .stream()
                    .filter(s -> s.estado().equals("A"))
                    .toList();
            if(!sesiones.isEmpty() && sesiones.size() >= responseLicenciaDTO.plan().getCantidadUsuarios()){
                AuthenticationEventsDTO authenticationEventsDTO = new AuthenticationEventsDTO(
                        usuarioCredencialesDTO.correoElectronico(),
                        authenticationEventsTypeService.getAuthenticationEventsTypeById(5),
                        "LoginServiceImp.login"
                );
                authenticationEventsService.createAuthenticationEvent(authenticationEventsDTO);
                throw new NoExisteException("La cantidad de usuarios en linea supera la cantidad de usuarios contratados.");
            }
            /**
             *
             */
        }
        String token = jwtUtils.generateToken(usuario.getId().toString(), crearClaims(usuario, this.getRolByUsuario(usuario)));
        sesionService.createSesion(new CreateSesionDTO(usuario.getEmpresa().getId(), usuario.getId()));
        AuthenticationEventsDTO authenticationEventsDTO = new AuthenticationEventsDTO(
                usuarioCredencialesDTO.correoElectronico(),
                authenticationEventsTypeService.getAuthenticationEventsTypeById(1),
                "LoginServiceImp.login"
        );
        authenticationEventsService.createAuthenticationEvent(authenticationEventsDTO);
        return new TokenDTO(token);
    }

    /**
     * Cierra la sesion activa de un usuario.
     * @param usuarioId identificador del usuario.
     * @return mensaje con el resultado del cierre de sesion.
     * @throws Exception si ocurre un error durante el proceso.
     */
    @Override
    public String cerrarSesion(Long usuarioId) throws Exception {
        Usuario usuario = this.usuarioService.getById(usuarioId);
        sesionService.inactiveSesion(usuario);
        return "La sesiÃ³n se encuentra desactivada.";
    }

    /**
     * Construye los claims personalizados del token JWT.
     * @param usuario usuario autenticado.
     * @param rolUsuario rol principal del usuario.
     * @return mapa de claims del token.
     */
    private Map<String, String> crearClaims(Usuario usuario, String rolUsuario){
        return Map.of(
                "email", usuario.getCorreoElectronico(),
                "nombre", usuario.getNombre1() + " " + usuario.getApellido1(),
                "rol", rolUsuario
        );
    }

    /**
     * Determina el rol principal de seguridad para un usuario.
     * @param usuario usuario autenticado.
     * @return nombre del rol principal.
     * @throws Exception si ocurre un error al consultar los roles asignados.
     */
    public String getRolByUsuario(Usuario usuario) throws Exception {
        List<ResponseRolUsuarioDTO> rolesUsuario = this.rolUsuarioService.getRolesUsuarioByUsuario(usuario);
        boolean esSuperAdmin = rolesUsuario.stream()
                .anyMatch(r -> "SUPER-ADMIN".equals(r.rol().getNombre()));

        if (esSuperAdmin) {
            return "SUPER-ADMIN";
        }

        boolean esAdmin = rolesUsuario.stream()
                .anyMatch(r -> "ADMIN".equals(r.rol().getNombre()));

        if (esAdmin) {
            return "ADMIN-EMPRESA";
        }

        return "OPERACION";
    }
}

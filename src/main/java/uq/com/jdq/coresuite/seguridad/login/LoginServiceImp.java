package uq.com.jdq.coresuite.seguridad.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uq.com.jdq.coresuite.config.JWTUtils;
import uq.com.jdq.coresuite.config.TokenDTO;
import uq.com.jdq.coresuite.infra.autenticationevents.AuthenticationEventsDTO;
import uq.com.jdq.coresuite.infra.autenticationevents.AuthenticationEventsService;
import uq.com.jdq.coresuite.infra.authenticationeventstype.AuthenticationEventsTypeService;
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

@Service
@RequiredArgsConstructor
public class LoginServiceImp implements LoginService {
    private final UsuarioService usuarioService;
    private final SesionService sesionService;
    private final LicenciaService licenciaService;
    private final JWTUtils jwtUtils;
    private final AuthenticationEventsService authenticationEventsService;
    private final AuthenticationEventsTypeService authenticationEventsTypeService;

    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception{
        UsuarioCredencialesDTO usuarioCredencialesDTO = new UsuarioCredencialesDTO(loginDTO.correoElectronico(), loginDTO.password());
        Usuario usuario = this.usuarioService.getUsuarioByCorreoElectronicoAndPassword(usuarioCredencialesDTO);
        if(!(usuario.apellido1.equals("ADMIN") || usuario.apellido2.equals("SUPER/ADMIN"))){
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
                throw new Exception("No existe una licencia vigente para esta empresa.");
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
                throw new Exception("La cantidad de usuarios en linea supera la cantidad de usuarios contratados.");
            }
        }
        String token = jwtUtils.generateToken(usuario.getId().toString(), crearClaims(usuario));
        sesionService.createSesion(new CreateSesionDTO(usuario.getEmpresa().getId(), usuario.getId()));
        AuthenticationEventsDTO authenticationEventsDTO = new AuthenticationEventsDTO(
                usuarioCredencialesDTO.correoElectronico(),
                authenticationEventsTypeService.getAuthenticationEventsTypeById(1),
                "LoginServiceImp.login"
        );
        authenticationEventsService.createAuthenticationEvent(authenticationEventsDTO);
        return new TokenDTO(token);
    }

    private Map<String, String> crearClaims(Usuario usuario){
        return Map.of(
                "email", usuario.getCorreoElectronico(),
                "nombre", usuario.getNombre1() + " " + usuario.getApellido1()
        );
    }

}

package uq.com.jdq.coresuite.seguridad.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uq.com.jdq.coresuite.config.JWTUtils;
import uq.com.jdq.coresuite.config.TokenDTO;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.seguridad.usuario.UsuarioCredencialesDTO;
import uq.com.jdq.coresuite.seguridad.usuario.UsuarioServiceImpl;
import uq.com.jdq.coresuite.sistema.sesion.CreateSesionDTO;
import uq.com.jdq.coresuite.sistema.sesion.SesionServiceImpl;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginServiceImp implements LoginService {
    private final UsuarioServiceImpl usuarioService;
    private final SesionServiceImpl sesionService;
    private final JWTUtils jwtUtils;


    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception{
        UsuarioCredencialesDTO usuarioCredencialesDTO = new UsuarioCredencialesDTO(loginDTO.correoElectronico(), loginDTO.password());
        Usuario usuario = this.usuarioService.getUsuarioByCorreoElectronicoAndPassword(usuarioCredencialesDTO);

        String token = jwtUtils.generateToken(usuario.getId().toString(), crearClaims(usuario));


        sesionService.createSesion(new CreateSesionDTO(usuario.getEmpresa().getId(), usuario.getId()));

        return new TokenDTO(token);
    }

    private Map<String, String> crearClaims(Usuario usuario){
        return Map.of(
                "email", usuario.getCorreoElectronico(),
                "nombre", usuario.getNombre1() + " " + usuario.getApellido1()
        );
    }

}

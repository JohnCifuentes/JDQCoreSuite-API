package uq.com.jdq.coresuite.seguridad.login;

import uq.com.jdq.coresuite.config.TokenDTO;

public interface LoginService {

    TokenDTO login(LoginDTO loginDTO) throws Exception;

    String cerrarSesion(String correoElectronico) throws Exception;

    String contactAdmin(String correoElectronico) throws Exception;

}

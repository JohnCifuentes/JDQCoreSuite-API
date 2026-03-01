package uq.com.jdq.coresuite.seguridad.login;

import uq.com.jdq.coresuite.config.TokenDTO;

public interface LoginService {

    public TokenDTO login(LoginDTO loginDTO) throws Exception;

}

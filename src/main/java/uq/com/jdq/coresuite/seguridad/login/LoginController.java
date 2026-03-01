package uq.com.jdq.coresuite.seguridad.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uq.com.jdq.coresuite.config.RespuestaDTO;
import uq.com.jdq.coresuite.config.TokenDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<RespuestaDTO<TokenDTO>> login(@RequestBody LoginDTO loginDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.loginService.login(loginDTO)));
    }

}

package uq.com.jdq.coresuite.infra.authenticationeventstype;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/infra/authenticatioEventsType")
public class AuthenticationEventsTypeController {
    private final AuthenticationEventsTypeService authenticationEventsTypeService;

    @GetMapping
    public ResponseEntity<RespuestaDTO<List<AuthenticationEventsTypeDTO>>> getAll() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.authenticationEventsTypeService.getAll()));
    };

    @GetMapping("/{id}/enventType")
    public ResponseEntity<RespuestaDTO<AuthenticationEventsTypeDTO>> getAuthenticationEventsTypeById(@PathVariable Integer id) throws Exception{
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.authenticationEventsTypeService.getAuthenticationEventsTypeById(id)));
    };

}

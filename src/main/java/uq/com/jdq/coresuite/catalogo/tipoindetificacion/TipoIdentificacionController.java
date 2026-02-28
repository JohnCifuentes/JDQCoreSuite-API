package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/catalogo/tipos-identificacion")
public class TipoIdentificacionController {
    private final TipoIdentificacionService tipoIdentificacionService;


    @GetMapping
    public ResponseEntity<RespuestaDTO<List<TipoIdentificacionDTO>>> getAllTiposIdentificacion() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.tipoIdentificacionService.getAllTiposIdentificacion()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO<TipoIdentificacionDTO>> getTipoIdentificacionById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok((new RespuestaDTO<>(false, this.tipoIdentificacionService.getTipoIdentificacionById(id))));
    }
}

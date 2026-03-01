package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoIdentificacionServiceImp implements TipoIdentificacionService {
    private final TipoIdentificacionRepository tipoIdentificacionRepository;
    private final TipoIdentificacionMapper tipoIdentificacionMapper;

    @Override
    public List<TipoIdentificacionDTO> getAllTiposIdentificacion() throws Exception {
        return tipoIdentificacionRepository.findAll().stream().map(tipoIdentificacionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TipoIdentificacionDTO getTipoIdentificacionById(Long id) throws Exception {
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(id).orElseThrow(() -> new Exception("Tipo de Identificacion no encontrado"));
        return tipoIdentificacionMapper.toDto(tipoIdentificacion);
    }

    public TipoIdentificacion getTipoIdentificacion(Long id) throws Exception {
        TipoIdentificacionDTO tipoIdentificacionDTO = getTipoIdentificacionById(id);
        System.out.println("Tipo de Identificacion: " + tipoIdentificacionDTO);
        return tipoIdentificacionMapper.toEntity(
                this.getTipoIdentificacionById(id)
        );
    }

}

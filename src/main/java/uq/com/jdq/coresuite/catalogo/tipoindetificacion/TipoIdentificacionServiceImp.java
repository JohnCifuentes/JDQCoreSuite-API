package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio de consulta de tipos de identificacion.
 */
@Service
@RequiredArgsConstructor
public class TipoIdentificacionServiceImp implements TipoIdentificacionService {
    private final TipoIdentificacionRepository tipoIdentificacionRepository;
    private final TipoIdentificacionMapper tipoIdentificacionMapper;

    /**
     * Obtiene la lista completa de tipos de identificacion.
     * @return lista de tipos de identificacion.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public List<TipoIdentificacionDTO> getAllTiposIdentificacion() throws Exception {
        return tipoIdentificacionRepository.findAll().stream().map(tipoIdentificacionMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Obtiene un tipo de identificacion por identificador.
     * @param id identificador del tipo de identificacion.
     * @return tipo de identificacion encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public TipoIdentificacionDTO getTipoIdentificacionById(Long id) throws Exception {
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(id).orElseThrow(() -> new Exception("Tipo de Identificacion no encontrado"));
        return tipoIdentificacionMapper.toDto(tipoIdentificacion);
    }

    /**
     * Obtiene la entidad TipoIdentificacion asociada a un identificador.
     * @param id identificador del tipo de identificacion.
     * @return entidad tipo de identificacion.
     * @throws Exception si ocurre un error durante la consulta.
     */
    public TipoIdentificacion getTipoIdentificacion(Long id) throws Exception {
        TipoIdentificacionDTO tipoIdentificacionDTO = getTipoIdentificacionById(id);
        return tipoIdentificacionMapper.toEntity(
                this.getTipoIdentificacionById(id)
        );
    }

}

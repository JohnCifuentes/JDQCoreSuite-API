package uq.com.jdq.coresuite.operacion.tipo_validacion;

import java.util.List;

public interface TipoValidacionService {

    ResponseTipoValidacionDTO createTipoValidacion(CreateTipoValidacionDTO createTipoValidacionDTO) throws Exception;

    ResponseTipoValidacionDTO updateTipoValidacion(Long id, UpdateTipoValidacionDTO updateTipoValidacionDTO) throws Exception;

    List<ResponseTipoValidacionDTO> getAllTipoValidaciones() throws Exception;

    ResponseTipoValidacionDTO getTipoValidacionById(Long id) throws Exception;

}

package uq.com.jdq.coresuite.operacion.campo_validacion;

import java.util.List;

public interface CampoValidacionService {

    ResponseCampoValidacionDTO createCampoValidacion(CreateCampoValidacionDTO createCampoValidacionDTO) throws Exception;

    ResponseCampoValidacionDTO updateCampoValidacion(Long id, UpdateCampoValidacionDTO updateCampoValidacionDTO) throws Exception;

    List<ResponseCampoValidacionDTO> getAllCampoValidaciones() throws Exception;

    ResponseCampoValidacionDTO getCampoValidacionById(Long id) throws Exception;

    List<ResponseCampoValidacionDTO> getCampoValidacionesByCampo(Long campoId) throws Exception;

}

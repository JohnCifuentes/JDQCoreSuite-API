package uq.com.jdq.coresuite.operacion.campo;

import java.util.List;

public interface CampoService {

    ResponseCampoDTO createCampo(CreateCampoDTO createCampoDTO) throws Exception;

    ResponseCampoDTO updateCampo(Long id, UpdateCampoDTO updateCampoDTO) throws Exception;

    List<ResponseCampoDTO> getAllCampos() throws Exception;

    ResponseCampoDTO getCampoById(Long id) throws Exception;

    List<ResponseCampoDTO> getCamposByInterfaz(Long interfazId) throws Exception;

}

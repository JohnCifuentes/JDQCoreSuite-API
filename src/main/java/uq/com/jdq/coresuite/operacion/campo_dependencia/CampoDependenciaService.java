package uq.com.jdq.coresuite.operacion.campo_dependencia;

import java.util.List;

public interface CampoDependenciaService {

    ResponseCampoDependenciaDTO createCampoDependencia(CreateCampoDependenciaDTO createCampoDependenciaDTO) throws Exception;

    ResponseCampoDependenciaDTO updateCampoDependencia(Long id, UpdateCampoDependenciaDTO updateCampoDependenciaDTO) throws Exception;

    List<ResponseCampoDependenciaDTO> getAllCampoDependencias() throws Exception;

    ResponseCampoDependenciaDTO getCampoDependenciaById(Long id) throws Exception;

    List<ResponseCampoDependenciaDTO> getCampoDependenciasByCampo(Long campoId) throws Exception;

}

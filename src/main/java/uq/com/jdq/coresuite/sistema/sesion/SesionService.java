package uq.com.jdq.coresuite.sistema.sesion;

import java.util.List;

public interface SesionService {

    ResponseSesionDTO createSesion(CreateSesionDTO createSesionDTO) throws Exception;

    ResponseSesionDTO updateSesion(Long id, UpdateSesionDTO updateSesionDTO) throws Exception;

    List<ResponseSesionDTO> getAllSesiones() throws Exception;

    ResponseSesionDTO getSesionById(Long id) throws Exception;

    List<ResponseSesionDTO> getSesionesByEmpresa(Long empresaId) throws Exception;

}

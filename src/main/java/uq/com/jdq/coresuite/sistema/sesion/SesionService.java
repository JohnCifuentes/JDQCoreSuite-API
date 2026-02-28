package uq.com.jdq.coresuite.sistema.sesion;

import java.util.List;

public interface SesionService {

    ResponseSesionDTO createSesion(CreateSesionDTO createSesionDTO);

    ResponseSesionDTO updateSesion(Long id, UpdateSesionDTO updateSesionDTO);

    ResponseSesionDTO inactiveSesion(Long id, InactiveSesionDTO inactiveSesionDTO);

    List<ResponseSesionDTO> getAllSesiones();

    ResponseSesionDTO getSesionById(Long id);

    List<ResponseSesionDTO> getSesionesByEmpresa(Long empresaId);

}

package uq.com.jdq.coresuite.sistema.licencia;

import java.util.List;

public interface LicenciaService {

    ResponseLicenciaDTO createLicencia(CreateLicenciaDTO createLicenciaDTO) throws Exception;

    ResponseLicenciaDTO updateLicencia(Long id, UpdateLicenciaDTO updateLicenciaDTO) throws Exception;

    ResponseLicenciaDTO inactiveLicencia(Long id, InactiveLicenciaDTO inactiveLicenciaDTO) throws Exception;

    List<ResponseLicenciaDTO> getAllLicencias() throws Exception;

    ResponseLicenciaDTO getLicenciaById(Long id) throws Exception;

    List<ResponseLicenciaDTO> getLicenciasByEmpresa(Long empresaId) throws Exception;

}

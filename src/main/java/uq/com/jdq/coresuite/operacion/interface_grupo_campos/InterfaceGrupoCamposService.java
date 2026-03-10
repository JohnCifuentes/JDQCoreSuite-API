package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

import java.util.List;

public interface InterfaceGrupoCamposService {

    ResponseInterfaceGrupoCamposDTO createInterfaceGrupoCampos(CreateInterfaceGrupoCamposDTO createInterfaceGrupoCamposDTO) throws Exception;

    ResponseInterfaceGrupoCamposDTO updateInterfaceGrupoCampos(Long id, UpdateInterfaceGrupoCamposDTO updateInterfaceGrupoCamposDTO) throws Exception;

    List<ResponseInterfaceGrupoCamposDTO> getAllInterfaceGrupoCampos() throws Exception;

    ResponseInterfaceGrupoCamposDTO getInterfaceGrupoCamposById(Long id) throws Exception;

    List<ResponseInterfaceGrupoCamposDTO> getInterfaceGrupoCamposByInterfaz(Long interfazId) throws Exception;

}

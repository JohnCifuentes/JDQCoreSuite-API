package uq.com.jdq.coresuite.sistema.empresa;

import java.util.List;

public interface EmpresaService {

    ResponseEmpresaDTO createEmpresa(CreateEmpresaDTO createEmpresaDTO) throws Exception;

    ResponseEmpresaDTO updateEmpresa(Long id, UpdateEmpresaDTO updateEmpresaDTO) throws Exception;

    ResponseEmpresaDTO inactiveEmpresa(Long id, InactiveEmpresaDTO inactiveEmpresaDTO) throws Exception;

    List<ResponseEmpresaDTO> getAllEmpresas() throws Exception;

    ResponseEmpresaDTO getEmpresaById(Long id) throws Exception;

}

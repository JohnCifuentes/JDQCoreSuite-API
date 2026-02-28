package uq.com.jdq.coresuite.catalogo.departamento;

import java.util.List;

public interface DepartamentoService {

    List<DepartamentoDTO> getAllDepartamentos() throws Exception;

    List<DepartamentoDTO> getAllDepartamentosByPais(Long paisId) throws Exception;

    DepartamentoDTO getDepartamentoById(Long id) throws Exception;

}

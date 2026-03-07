package uq.com.jdq.coresuite.operacion.modulo;

import java.util.List;

public interface ModuloService {

    ResponseModuloDTO createModulo(CreateModuloDTO createModuloDTO) throws Exception;

    ResponseModuloDTO updateModulo(Long id, UpdateModuloDTO updateModuloDTO) throws Exception;

    List<ResponseModuloDTO> getAllModulos() throws Exception;

    ResponseModuloDTO getModuloById(Long id) throws Exception;

    List<ResponseModuloDTO> getModulosByEmpresa(Long empresaId) throws Exception;

}

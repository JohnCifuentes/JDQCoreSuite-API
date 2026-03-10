package uq.com.jdq.coresuite.operacion.interfaz;

import java.util.List;

public interface InterfazService {

    ResponseInterfazDTO createInterfaz(CreateInterfazDTO createInterfazDTO) throws Exception;

    ResponseInterfazDTO updateInterfaz(Long id, UpdateInterfazDTO updateInterfazDTO) throws Exception;

    List<ResponseInterfazDTO> getAllInterfaz() throws Exception;

    ResponseInterfazDTO getInterfazById(Long id) throws Exception;

    List<ResponseInterfazDTO> getInterfazByModulo(Long moduloId) throws Exception;

}

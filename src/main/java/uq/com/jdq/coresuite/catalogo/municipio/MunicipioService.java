package uq.com.jdq.coresuite.catalogo.municipio;

import java.util.List;

public interface MunicipioService {

    List<MunicipioDTO> getAllMunicipios() throws Exception;

    List<MunicipioDTO> getAllMunicipiosByDepartamento(Long departamentoId) throws Exception;

    MunicipioDTO getMunicipioById(Long id) throws Exception;

}

package uq.com.jdq.coresuite.catalogo.pais;

import java.util.List;

public interface PaisService {

    List<PaisDTO> getAllPaises() throws Exception;

    PaisDTO getPaisById(Long id) throws Exception;

}

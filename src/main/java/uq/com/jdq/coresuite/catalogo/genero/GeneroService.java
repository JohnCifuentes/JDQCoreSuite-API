package uq.com.jdq.coresuite.catalogo.genero;

import java.util.List;

public interface GeneroService {

    List<GeneroDTO> getAllGeneros() throws Exception;

    GeneroDTO getGeneroById(Long id) throws Exception;

}

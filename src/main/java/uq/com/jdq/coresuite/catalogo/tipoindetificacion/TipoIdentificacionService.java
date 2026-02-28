package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

import java.util.List;

public interface TipoIdentificacionService {

    List<TipoIdentificacionDTO> getAllTiposIdentificacion() throws Exception;

    TipoIdentificacionDTO getTipoIdentificacionById(Long id) throws Exception;

}

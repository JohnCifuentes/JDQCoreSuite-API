package uq.com.jdq.coresuite.operacion.tipo_campo;

import java.util.List;

public interface TipoCampoService {

    ResponseTipoCampoDTO createTipoCampo(CreateTipoCampoDTO createTipoCampoDTO) throws Exception;

    ResponseTipoCampoDTO updateTipoCampo(Long id, UpdateTipoCampoDTO updateTipoCampoDTO) throws Exception;

    List<ResponseTipoCampoDTO> getAllTipoCampos() throws Exception;

    ResponseTipoCampoDTO getTipoCampoById(Long id) throws Exception;

}

package uq.com.jdq.coresuite.seguridad.codigo;

public interface CodigoService {

    void generate(CreateCodigoDTO codigoDTO) throws Exception;

    boolean confirmarCodigo(ConfirmarUsuarioCodigoDTO codigoDTO) throws Exception;


}

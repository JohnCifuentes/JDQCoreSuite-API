package uq.com.jdq.coresuite.seguridad.codigo;

public interface CodigoService {

    String generate(CreateCodigoDTO codigoDTO) throws Exception;

    String confirmarCodigo(ConfirmarUsuarioCodigoDTO codigoDTO) throws Exception;

}

package uq.com.jdq.coresuite.seguridad.codigo;

/**
 * Contrato de negocio para la generacion y confirmacion de codigos de verificacion.
 */
public interface CodigoService {

    /**
     * Genera un nuevo codigo de verificacion.
     * @param codigoDTO datos del usuario destinatario.
     * @return mensaje con el resultado de la operacion.
     * @throws Exception si ocurre un error durante la generacion.
     */
    String generate(CreateCodigoDTO codigoDTO) throws Exception;

    /**
     * Confirma un codigo de verificacion emitido previamente.
     * @param codigoDTO datos del usuario y codigo a validar.
     * @return mensaje con el resultado de la confirmacion.
     * @throws Exception si ocurre un error durante la validacion.
     */
    String confirmarCodigo(ConfirmarUsuarioCodigoDTO codigoDTO) throws Exception;

}

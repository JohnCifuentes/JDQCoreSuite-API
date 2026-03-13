package uq.com.jdq.coresuite.notificacion;

/**
 * Define la estructura y comportamiento de record EmailDTO.
 */
public record EmailDTO(
        String asunto,
        String cuerpo,
        String destinatario
) {
}

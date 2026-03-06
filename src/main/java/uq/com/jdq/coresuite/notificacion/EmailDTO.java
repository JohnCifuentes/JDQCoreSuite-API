package uq.com.jdq.coresuite.notificacion;

public record EmailDTO(
        String asunto,
        String cuerpo,
        String destinatario
) {
}

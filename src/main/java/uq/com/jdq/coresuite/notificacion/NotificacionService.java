package uq.com.jdq.coresuite.notificacion;

/**
 * Define la estructura y comportamiento de interface NotificacionService.
 */
public interface NotificacionService {

    void enviarNotificacion(EmailDTO emailDTO) throws Exception;

}

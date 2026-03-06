package uq.com.jdq.coresuite.notificacion;

import lombok.RequiredArgsConstructor;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class NotificacionServiceImp implements NotificacionService {

    @Async
    @Override
    public void enviarNotificacion(EmailDTO emailDTO) {
        Email email = EmailBuilder.startingBlank()
                .from("jdqsolutionssas@gmail.com")
                .to(emailDTO.destinatario())
                .withSubject(emailDTO.asunto())
                .withPlainText(emailDTO.cuerpo())
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "jdqsolutionsSAS@Gmail.com", "ccuu scyf duzo iwwl")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()){
            mailer.sendMail(email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}

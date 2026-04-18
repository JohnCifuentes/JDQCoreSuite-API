package uq.com.jdq.coresuite.seguridad.codigo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.config.exceptions.ReglasCodigoException;
import uq.com.jdq.coresuite.notificacion.EmailDTO;
import uq.com.jdq.coresuite.notificacion.NotificacionService;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.seguridad.usuario.UsuarioServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementacion del servicio de generacion y confirmacion de codigos de verificacion.
 */
@Service
@RequiredArgsConstructor
public class CodigoServiceImpl implements CodigoService {

    private final CodigoRepository codigoRepository;
    private final UsuarioServiceImpl usuarioService;
    private final NotificacionService notificacionService;

    /**
     * Genera un nuevo codigo de verificacion y lo notifica al usuario.
     * @param codigoDTO datos del usuario destinatario.
     * @return mensaje con el resultado de la operacion.
     * @throws Exception si ocurre un error durante la generacion o notificacion.
     */
    @Override
    public String generate(CreateCodigoDTO codigoDTO) throws Exception {
        Usuario usuario = getUsuarioByCorreoElectronico(codigoDTO.correoElectronico());
        Codigo codigo = new Codigo();
        codigo.setUsuario(usuario);
        codigo.setCodigo(generarCodigoAleatorio());
        codigoRepository.save(codigo);
        String cuerpo = """
        <!DOCTYPE html>
        <html>
        <head>
          <meta charset="UTF-8">
        </head>
        <body style="margin:0; padding:0; background-color:#f4f6f8; font-family: Arial, sans-serif;">
          <table width="100%%" cellpadding="0" cellspacing="0" style="background-color:#f4f6f8; padding:20px 0;">
            <tr>
              <td align="center">
                <table width="600" cellpadding="0" cellspacing="0" style="background-color:#ffffff; border-radius:8px; overflow:hidden;">
                  <tr>
                    <td style="background-color:#1f3c88; padding:20px; text-align:center; color:white; font-size:20px; font-weight:bold;">
                      JDQ - CoreSuite
                    </td>
                  </tr>
                  <tr>
                    <td style="padding:30px; color:#333333; font-size:14px; line-height:1.6;">
                      <p>Hola,</p>
                      <p>Hemos recibido una solicitud para restablecer la contraseña de su cuenta en <strong>JDQ - CoreSuite</strong>.</p>
                      <p>Utilice el siguiente código para continuar con el proceso de restablecimiento de contraseña:</p>
                      <table width="100%%" cellpadding="0" cellspacing="0" style="margin:25px 0; text-align:center;">
                        <tr>
                          <td style="background-color:#1f3c88; color:#ffffff; font-size:22px; font-weight:bold; padding:15px; border-radius:6px; letter-spacing:2px;">
                            %s
                          </td>
                        </tr>
                      </table>
                      <p style="text-align:center; font-size:13px; color:#777;">
                        Este código expirará en <strong>%s minutos</strong>.
                      </p>
                      <p style="background-color:#fdecea; padding:12px; border-radius:6px; font-size:13px; color:#a94442;">
                        Si usted no solicitó este cambio, puede ignorar este correo. Su contraseña actual permanecerá sin cambios.
                      </p>
                      <p style="background-color:#fff4e5; padding:12px; border-radius:6px; font-size:13px; color:#8a6d3b;">
                        Por seguridad, no comparta este código con nadie.
                      </p>
                      <p>
                        Atentamente,<br>
                        <strong>Equipo JDQ - CoreSuite</strong>
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td style="background-color:#f1f1f1; text-align:center; padding:15px; font-size:12px; color:#777;">
                      © 2026 JDQ - CoreSuite. Todos los derechos reservados.
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </body>
        </html>
        """.formatted(codigo.getCodigo(), 15);
        EmailDTO emailDTO = new EmailDTO("Olvide mi contraseña JDQ - CoreSuite", cuerpo, usuario.getCorreoElectronico());
        notificacionService.enviarNotificacion(emailDTO);
        return "Codigo generado correctamente";
    }

    @Override
    public String generate2FA(CreateCodigoDTO codigoDTO) throws Exception {
        Usuario usuario = getUsuarioByCorreoElectronico(codigoDTO.correoElectronico());
        Codigo codigo = new Codigo();
        codigo.setUsuario(usuario);
        codigo.setCodigo(generarCodigoAleatorio());
        codigoRepository.save(codigo);
        String cuerpo = """
        <!DOCTYPE html>
        <html>
        <head>
          <meta charset="UTF-8">
        </head>
        <body style="margin:0; padding:0; background-color:#f4f6f8; font-family: Arial, sans-serif;">
          <table width="100%%" cellpadding="0" cellspacing="0" style="background-color:#f4f6f8; padding:20px 0;">
            <tr>
              <td align="center">
                <table width="600" cellpadding="0" cellspacing="0" style="background-color:#ffffff; border-radius:8px; overflow:hidden;">
                  <tr>
                    <td style="background-color:#1f3c88; padding:20px; text-align:center; color:white; font-size:20px; font-weight:bold;">
                      JDQ - CoreSuite
                    </td>
                  </tr>
                  <tr>
                    <td style="padding:30px; color:#333333; font-size:14px; line-height:1.6;">
                      <p>Hola,</p>
                      <p>Hemos detectado un intento de inicio de sesión en su cuenta de <strong>JDQ - CoreSuite</strong>.</p>
                      <p>Para completar el acceso, ingrese el siguiente código de verificación:</p>
                      <table width="100%%" cellpadding="0" cellspacing="0" style="margin:25px 0; text-align:center;">
                        <tr>
                          <td style="background-color:#1f3c88; color:#ffffff; font-size:22px; font-weight:bold; padding:15px; border-radius:6px; letter-spacing:2px;">
                            %s
                          </td>
                        </tr>
                      </table>
                      <p style="text-align:center; font-size:13px; color:#777;">
                        Este código es válido por <strong>%s minutos</strong>.
                      </p>
                      <p>Si usted está intentando iniciar sesión, introduzca este código en la aplicación para continuar.</p>
                      <p style="background-color:#fdecea; padding:12px; border-radius:6px; font-size:13px; color:#a94442;">
                        Si no reconoce este intento, le recomendamos cambiar su contraseña inmediatamente y contactar con el equipo de soporte.
                      </p>
                      <p style="background-color:#fff4e5; padding:12px; border-radius:6px; font-size:13px; color:#8a6d3b;">
                        Por seguridad, no comparta este código con nadie.
                      </p>
                      <p>
                        Atentamente,<br>
                        <strong>Equipo JDQ - CoreSuite</strong>
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td style="background-color:#f1f1f1; text-align:center; padding:15px; font-size:12px; color:#777;">
                      © 2026 JDQ - CoreSuite. Todos los derechos reservados.
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </body>
        </html>
        """.formatted(codigo.getCodigo(), 15);
        EmailDTO emailDTO = new EmailDTO("Tu código de verificación - JDQ CoreSuite", cuerpo, usuario.getCorreoElectronico());
        notificacionService.enviarNotificacion(emailDTO);
        return "Codigo generado correctamente";
    }


    /**
     * Confirma un codigo de verificacion vigente para un usuario.
     * @param codigoDTO datos del usuario y codigo a validar.
     * @return mensaje con el resultado de la confirmacion.
     * @throws Exception si ocurre un error durante la validacion.
     */
    @Override
    public String confirmarCodigo(ConfirmarUsuarioCodigoDTO codigoDTO) throws Exception {
        Usuario usuario = getUsuarioByCorreoElectronico(codigoDTO.correoElectronico());
        Optional<Codigo> codigo = codigoRepository.findTopByUsuarioIdOrderByFechaGeneracionDesc(usuario.getId());
        if(codigo.isPresent()){
            Codigo codigoAux = codigo.get();
            if(codigoAux.getFechaGeneracion().plusMinutes(15).isBefore(LocalDateTime.now())){
                throw new ReglasCodigoException("El código de confimación ha expirado. Solicite uno nuevamente");
            }
            if(!codigoAux.getEstado().equals("A")){
                throw new ReglasCodigoException("El código de confirmación ya fue usado. Solicite uno nuevamente");
            }
            codigoAux.setEstado("I");
            codigoRepository.save(codigoAux);
            return "Código confirmado correctamente";
        } else {
            throw new NoExisteException("No se ha generado un código de confirmación para este usuario");
        }
    }

    /**
     * Genera un codigo aleatorio para procesos de verificacion.
     * @return codigo generado.
     */
    public String generarCodigoAleatorio(){
        String digitos="0123456789ABCDEFGHI(/&%$#";
        StringBuilder codigo=new StringBuilder();
        for(int i=0; i<6; i++){
            int indice=(int) (Math.random()*digitos.length());
            codigo.append(digitos.charAt(indice));
        }
        return codigo.toString();
    }

    /**
     * Se obtiene el usuario dado el correo electrónico
     * @param correoElectronico correo electrónico del usuario
     * @return usuario dado el correo electrónico
     * @throws Exception si ocurre un error durante la validacion.
     */
    public Usuario getUsuarioByCorreoElectronico(String correoElectronico) throws Exception {
        Optional<Usuario> usuario = usuarioService.getUsuarioByCorreoElectronico(correoElectronico);
        if(usuario.isEmpty()) {
            throw new NoExisteException("Correo electrónico no encontrado");
        }
        return usuario.get();
    }

}

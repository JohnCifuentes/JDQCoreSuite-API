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
        Optional<Usuario> usuario = usuarioService.getUsuarioByCorreoElectronico(codigoDTO.correoElectronico());
        if(usuario.isEmpty()) {
            throw new NoExisteException("Correo electrÃ³nico no encontrado");
        }
        Codigo codigo = new Codigo();
        codigo.setUsuario(usuario.get());
        codigo.setCodigo(generarCodigoAleatorio());
        codigoRepository.save(codigo);
        /**
         *
         */
        String cuerpo = """
        Hola,
        
        Hemos recibido una solicitud para restablecer la contraseÃ±a de su cuenta en JDQ - CoreSuite.
        
        Utilice el siguiente cÃ³digo para continuar con el proceso de restablecimiento de contraseÃ±a:
        
        CÃ³digo de verificaciÃ³n: %s
        
        Este cÃ³digo expirarÃ¡ en %s minutos.
        
        Si usted no solicitÃ³ este cambio, puede ignorar este correo. Su contraseÃ±a actual permanecerÃ¡ sin cambios.
        
        Por seguridad, no comparta este cÃ³digo con nadie.
        
        Atentamente,
        Equipo JDQ - CoreSuite
        """.formatted(codigo.getCodigo(), 15);
        EmailDTO emailDTO = new EmailDTO("Olvide mi contraseÃ±a JDQ - CoreSuite", cuerpo, usuario.get().getCorreoElectronico());
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
        Optional<Usuario> usuario = usuarioService.getUsuarioByCorreoElectronico(codigoDTO.correoElectronico());
        if(usuario.isEmpty()) {
            throw new NoExisteException("Correo electrÃ³nico no encontrado");
        }
        Optional<Codigo> codigo = codigoRepository.findTopByUsuarioIdOrderByFechaGeneracionDesc(usuario.get().getId());
        if(codigo.isPresent()){
            Codigo codigoAux = codigo.get();
            if(codigoAux.getFechaGeneracion().plusMinutes(15).isBefore(LocalDateTime.now())){
                throw new ReglasCodigoException("El cÃ³digo de confimaciÃ³n ha expirado. Solicite uno nuevamente");
            }
            if(!codigoAux.getEstado().equals("A")){
                throw new ReglasCodigoException("El cÃ³digo de confirmaciÃ³n ya fue usado. Solicite uno nuevamente");
            }
            codigoAux.setEstado("I");
            codigoRepository.save(codigoAux);
            return "CÃ³digo confirmado correctamente";
        } else {
            throw new NoExisteException("No se ha generado un cÃ³digo de confirmaciÃ³n para este usuario");
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

}

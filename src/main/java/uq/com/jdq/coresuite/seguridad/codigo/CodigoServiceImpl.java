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

@Service
@RequiredArgsConstructor
public class CodigoServiceImpl implements CodigoService {

    private final CodigoRepository codigoRepository;
    private final UsuarioServiceImpl usuarioService;
    private final NotificacionService notificacionService;

    @Override
    public String generate(CreateCodigoDTO codigoDTO) throws Exception {
        Optional<Usuario> usuario = usuarioService.getUsuarioByCorreoElectronico(codigoDTO.correoElectronico());
        if(usuario.isEmpty()) {
            throw new NoExisteException("Correo electrónico no encontrado");
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
        
        Hemos recibido una solicitud para restablecer la contraseña de su cuenta en JDQ - CoreSuite.
        
        Utilice el siguiente código para continuar con el proceso de restablecimiento de contraseña:
        
        Código de verificación: %s
        
        Este código expirará en %s minutos.
        
        Si usted no solicitó este cambio, puede ignorar este correo. Su contraseña actual permanecerá sin cambios.
        
        Por seguridad, no comparta este código con nadie.
        
        Atentamente,
        Equipo JDQ - CoreSuite
        """.formatted(codigo.getCodigo(), 15);
        EmailDTO emailDTO = new EmailDTO("Olvide mi contraseña JDQ - CoreSuite", cuerpo, usuario.get().getCorreoElectronico());
        notificacionService.enviarNotificacion(emailDTO);
        return "Codigo generado correctamente";
    }

    @Override
    public String confirmarCodigo(ConfirmarUsuarioCodigoDTO codigoDTO) throws Exception {
        Optional<Usuario> usuario = usuarioService.getUsuarioByCorreoElectronico(codigoDTO.correoElectronico());
        if(usuario.isEmpty()) {
            throw new NoExisteException("Correo electrónico no encontrado");
        }
        Optional<Codigo> codigo = codigoRepository.findTopByUsuarioIdOrderByFechaGeneracionDesc(usuario.get().getId());
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

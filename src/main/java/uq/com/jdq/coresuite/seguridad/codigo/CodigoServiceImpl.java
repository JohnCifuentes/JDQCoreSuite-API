package uq.com.jdq.coresuite.seguridad.codigo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.seguridad.usuario.UsuarioServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodigoServiceImpl implements CodigoService {

    private final CodigoRepository codigoRepository;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public void generate(CreateCodigoDTO codigoDTO) throws Exception {
        Optional<Usuario> usuario = usuarioService.getUsuarioByCorreoElectronico(codigoDTO.correoElectronico());
        if(usuario.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }
        Codigo codigo = new Codigo();
        codigo.setUsuario(usuario.get());
        codigo.setCodigo(generarCodigoAleatorio());
        codigoRepository.save(codigo);
    }

    @Override
    public boolean confirmarCodigo(ConfirmarUsuarioCodigoDTO codigoDTO) throws Exception {
        Optional<Usuario> usuario = usuarioService.getUsuarioByCorreoElectronico(codigoDTO.correoElectronico());
        if(usuario.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }
        Optional<Codigo> codigo = codigoRepository.findTopByUsuarioIdOrderByFechaGeneracionDesc(usuario.get().getId());
        if(codigo.isPresent()){
            Codigo codigoAux = codigo.get();
            if(codigoAux.getFechaGeneracion().plusMinutes(15).isBefore(LocalDateTime.now())){
                throw new Exception("El código de confimación ha expirado. Solicite uno nuevamente");
            }
            if(!codigoAux.getEstado().equals("A")){
                throw new Exception("El código de confirmación ya fue usado. Solicite uno nuevamente");
            }
            codigoAux.setEstado("I");
            codigoRepository.save(codigoAux);
            return true;
        } else {
            throw new Exception("No se ha generado un código de confirmación para este usuario");
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

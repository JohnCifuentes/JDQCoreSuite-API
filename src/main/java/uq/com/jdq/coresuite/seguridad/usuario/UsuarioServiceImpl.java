package uq.com.jdq.coresuite.seguridad.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacion;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacionRepository;
import uq.com.jdq.coresuite.config.SecurityConfig;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final EmpresaRepository empresaRepository;
    private final TipoIdentificacionRepository tipoIdentificacionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseUsuarioDTO createUsuario(CreateUsuarioDTO createUsuarioDTO) throws Exception {
        Usuario usuario = usuarioMapper.toEntity(createUsuarioDTO);
        Empresa empresa = empresaRepository.findById(createUsuarioDTO.empresaId()).orElseThrow(
            () -> new RuntimeException("No existe la empresa")
        );
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(createUsuarioDTO.tipoIdentificacionId()).orElseThrow(
            () -> new RuntimeException("No existe el tipo de identificacion")
        );
        usuario.setEmpresa(empresa);
        usuario.setTipoIdentificacion(tipoIdentificacion);
        if(!this.getUsuarioByCorreoElectronico(createUsuarioDTO.correoElectronico()).isEmpty()){
            throw new RuntimeException("El correo electronico ya existe");
        }
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    @Transactional
    public ResponseUsuarioDTO updateUsuario(Long id, UpdateUsuarioDTO updateUsuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Empresa empresa = empresaRepository.findById(updateUsuarioDTO.empresaId()).orElseThrow(() ->
                new RuntimeException("No existe la empresa")
        );
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(updateUsuarioDTO.tipoIdentificacionId()).orElseThrow( () ->
                new RuntimeException("No existe el tipo de identificacion")
        );
        usuarioMapper.updateEntityFromDTO(updateUsuarioDTO, usuario);
        usuario.setEmpresa(empresa);
        usuario.setTipoIdentificacion(tipoIdentificacion);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    @Transactional
    public ResponseUsuarioDTO inactiveUsuario(Long id, InactiveUsuarioDTO inactiveUsuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioMapper.inactiveEntityFromDTO(inactiveUsuarioDTO, usuario);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseUsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseUsuarioDTO getUsuarioById(Long id) {
        Optional<Usuario> usuariobd = usuarioRepository.findById(id);
        ResponseUsuarioDTO responseUsuarioDTO = usuarioMapper.toDTO(usuariobd.get());
        return responseUsuarioDTO;
    }

    @Override
    public List<ResponseUsuarioDTO> getUsuariosByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() ->
                new RuntimeException("No existe la empresa")
        );
        return usuarioRepository.findByEmpresa(empresa);
    }

    @Override
    public Usuario getUsuarioByCorreoElectronicoAndPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception {
        Optional<Usuario> usuario = this.getUsuarioByCorreoElectronico(usuarioCredencialesDTO.correoElectronico());
        if(usuario.isEmpty()){
            throw new RuntimeException("No existe el correo electronico");
        }
        if(!passwordEncoder.matches(usuarioCredencialesDTO.password(), usuario.get().getPassword())){
            throw new RuntimeException("Credenciales incorrectas");
        }
        return usuario.get();
    }

    @Override
    public ResponseUsuarioDTO asignarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception {
        Optional<Usuario> usuario = this.getUsuarioByCorreoElectronico(usuarioCredencialesDTO.correoElectronico());
        if(usuario.isPresent()){
            Usuario usuarioAux = usuario.get();
            usuarioAux.setPassword(passwordEncoder.encode(usuarioCredencialesDTO.password()));
            usuarioRepository.save(usuarioAux);
            return usuarioMapper.toDTO(usuarioAux);
        } else {
            throw new RuntimeException("No existe el usuario");
        }
    }

    @Override
    public ResponseUsuarioDTO recuperarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception {
        Optional<Usuario> usuario = this.getUsuarioByCorreoElectronico(usuarioCredencialesDTO.correoElectronico());
        if(usuario.isPresent()){
            Usuario usuarioAux = usuario.get();
            usuarioAux.setPassword(passwordEncoder.encode(usuarioCredencialesDTO.password()));
            usuarioRepository.save(usuarioAux);
            return usuarioMapper.toDTO(usuarioAux);
        } else {
            throw new RuntimeException("No existe el usuario");
        }
    }

    @Override
    public ResponseUsuarioDTO actualizarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception {
        Optional<Usuario> usuario = this.getUsuarioByCorreoElectronico(usuarioCredencialesDTO.correoElectronico());
        if(usuario.isPresent()){
            Usuario usuarioAux = usuario.get();
            usuarioAux.setPassword(passwordEncoder.encode(usuarioCredencialesDTO.password()));
            usuarioRepository.save(usuarioAux);
            return usuarioMapper.toDTO(usuarioAux);
        } else {
            throw new RuntimeException("No existe el usuario");
        }
    }

    public Optional<Usuario> getUsuarioByCorreoElectronico(String correoElectronico) throws Exception {
        return usuarioRepository.findByCorreoElectronico(correoElectronico);
    }

}

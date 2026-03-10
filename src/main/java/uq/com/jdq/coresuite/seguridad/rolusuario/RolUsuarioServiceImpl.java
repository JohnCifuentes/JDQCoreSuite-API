package uq.com.jdq.coresuite.seguridad.rolusuario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.seguridad.rol.Rol;
import uq.com.jdq.coresuite.seguridad.rol.RolRepository;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.seguridad.usuario.UsuarioRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolUsuarioServiceImpl implements RolUsuarioService {

    private final RolUsuarioRepository rolUsuarioRepository;
    private final RolUsuarioMapper rolUsuarioMapper;
    private final EmpresaRepository empresaRepository;
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public ResponseRolUsuarioDTO createRolUsuario(CreateRolUsuarioDTO createRolUsuarioDTO) throws Exception {
        RolUsuario rolUsuario = rolUsuarioMapper.toEntity(createRolUsuarioDTO);
        Empresa empresa = empresaRepository.findById(createRolUsuarioDTO.empresaId()).orElseThrow(() ->
                new NoExisteException("No existe la empresa")
        );
        Rol rol = rolRepository.findById(createRolUsuarioDTO.rolId()).orElseThrow(() ->
                new NoExisteException("No existe el rol")
        );
        Usuario usuario = usuarioRepository.findById(createRolUsuarioDTO.usuarioId()).orElseThrow(() ->
                new NoExisteException("No existe el usuario")
        );
        rolUsuario.setEmpresa(empresa);
        rolUsuario.setRol(rol);
        rolUsuario.setUsuario(usuario);
        rolUsuario = rolUsuarioRepository.save(rolUsuario);
        return rolUsuarioMapper.toDTO(rolUsuario);
    }

    @Override
    @Transactional
    public ResponseRolUsuarioDTO updateRolUsuario(Long id, UpdateRolUsuarioDTO updateRolUsuarioDTO) throws Exception {
        RolUsuario rolUsuario = rolUsuarioRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el rol usuario"));
        Empresa empresa = empresaRepository.findById(updateRolUsuarioDTO.empresaId()).orElseThrow(() ->
                new NoExisteException("No existe la empresa")
        );
        Rol rol = rolRepository.findById(updateRolUsuarioDTO.rolId()).orElseThrow(() ->
                new NoExisteException("No existe el rol")
        );
        Usuario usuario = usuarioRepository.findById(updateRolUsuarioDTO.usuarioId()).orElseThrow(() ->
                new NoExisteException("No existe el usuario")
        );
        rolUsuario.setEmpresa(empresa);
        rolUsuario.setRol(rol);
        rolUsuario.setUsuario(usuario);
        rolUsuarioMapper.updateEntityFromDTO(updateRolUsuarioDTO, rolUsuario);
        rolUsuario = rolUsuarioRepository.save(rolUsuario);
        return rolUsuarioMapper.toDTO(rolUsuario);
    }

    @Override
    @Transactional
    public ResponseRolUsuarioDTO inactiveRolUsuario(Long id, InactiveRolUsuarioDTO inactiveRolUsuarioDTO) throws Exception {
        RolUsuario rolUsuario = rolUsuarioRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el rol usuario"));
        rolUsuarioMapper.inactiveEntityFromDTO(inactiveRolUsuarioDTO, rolUsuario);
        rolUsuario = rolUsuarioRepository.save(rolUsuario);
        return rolUsuarioMapper.toDTO(rolUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseRolUsuarioDTO> getAllRolUsuarios() throws Exception {
        return rolUsuarioRepository.findAll().stream()
                .map(rolUsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseRolUsuarioDTO getRolUsuarioById(Long id) throws Exception {
        return rolUsuarioRepository.findById(id)
                .map(rolUsuarioMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe el rol usuario"));
    }

    @Override
    public List<ResponseRolUsuarioDTO> getRolUsuariosByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() ->
                new NoExisteException("No existe la empresa")
        );
        return rolUsuarioRepository.findByEmpresa(empresa);
    }

}

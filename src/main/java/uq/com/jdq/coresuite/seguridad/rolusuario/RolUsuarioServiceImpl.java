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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio encargado de administrar asignaciones rol-usuario.
 */
@Service
@RequiredArgsConstructor
public class RolUsuarioServiceImpl implements RolUsuarioService {

    private final RolUsuarioRepository rolUsuarioRepository;
    private final RolUsuarioMapper rolUsuarioMapper;
    private final EmpresaRepository empresaRepository;
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;

        /**
         * Registra una asignacion entre rol y usuario o reactiva la existente si ya fue creada antes.
         * @param createRolUsuarioDTO datos de la asignacion.
         * @return asignacion creada o reactivada.
         * @throws Exception si la empresa, el rol o el usuario no existen.
         */
    @Override
    @Transactional
    public ResponseRolUsuarioDTO createRolUsuario(CreateRolUsuarioDTO createRolUsuarioDTO) throws Exception {
        Empresa empresa = empresaRepository.findById(createRolUsuarioDTO.empresaId()).orElseThrow(() ->
                new NoExisteException("No existe la empresa")
        );
        Rol rol = rolRepository.findById(createRolUsuarioDTO.rolId()).orElseThrow(() ->
                new NoExisteException("No existe el rol")
        );
        Usuario usuario = usuarioRepository.findById(createRolUsuarioDTO.usuarioId()).orElseThrow(() ->
                new NoExisteException("No existe el usuario")
        );

        RolUsuario rolUsuario = rolUsuarioRepository.findByUsuarioAndRol(usuario, rol)
                .map(rolUsuarioExistente -> {
                    rolUsuarioExistente.setEstado("A");
                    rolUsuarioExistente.setUsuarioActualizacion("PRUEBAS");
                    rolUsuarioExistente.setFechaActualizacion(LocalDateTime.now());
                    return rolUsuarioExistente;
                })
                .orElseGet(() -> {
                    RolUsuario nuevoRolUsuario = rolUsuarioMapper.toEntity(createRolUsuarioDTO);
                    nuevoRolUsuario.setEmpresa(empresa);
                    nuevoRolUsuario.setRol(rol);
                    nuevoRolUsuario.setUsuario(usuario);
                    return nuevoRolUsuario;
                });

        rolUsuario = rolUsuarioRepository.save(rolUsuario);
        return rolUsuarioMapper.toDTO(rolUsuario);
    }

        /**
         * Actualiza una asignacion existente entre rol y usuario.
         * @param id identificador de la asignacion.
         * @param updateRolUsuarioDTO nuevos datos de la asignacion.
         * @return asignacion actualizada.
         * @throws Exception si la asignacion, la empresa, el rol o el usuario no existen.
         */
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

        /**
         * Cambia el estado de una asignacion rol-usuario.
         * @param id identificador de la asignacion.
         * @param inactiveRolUsuarioDTO datos del nuevo estado.
         * @return asignacion actualizada.
         * @throws Exception si la asignacion no existe.
         */
    @Override
    @Transactional
    public ResponseRolUsuarioDTO inactiveRolUsuario(Long id, InactiveRolUsuarioDTO inactiveRolUsuarioDTO) throws Exception {
        RolUsuario rolUsuario = rolUsuarioRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el rol usuario"));
        rolUsuarioMapper.inactiveEntityFromDTO(inactiveRolUsuarioDTO, rolUsuario);
        rolUsuario = rolUsuarioRepository.save(rolUsuario);
        return rolUsuarioMapper.toDTO(rolUsuario);
    }

        /**
         * Obtiene todas las asignaciones registradas entre roles y usuarios.
         * @return lista de asignaciones.
         * @throws Exception si ocurre un error durante la consulta.
         */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseRolUsuarioDTO> getAllRolUsuarios() throws Exception {
        return rolUsuarioRepository.findAll().stream()
                .map(rolUsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

        /**
         * Consulta una asignacion por su identificador.
         * @param id identificador de la asignacion.
         * @return asignacion encontrada.
         * @throws Exception si la asignacion no existe.
         */
    @Override
    @Transactional(readOnly = true)
    public ResponseRolUsuarioDTO getRolUsuarioById(Long id) throws Exception {
        return rolUsuarioRepository.findById(id)
                .map(rolUsuarioMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe el rol usuario"));
    }

        /**
         * Obtiene las asignaciones de rol-usuario de una empresa.
         * @param empresaId identificador de la empresa.
         * @return lista de asignaciones.
         * @throws Exception si la empresa no existe.
         */
    @Override
    public List<ResponseRolUsuarioDTO> getRolUsuariosByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() ->
                new NoExisteException("No existe la empresa")
        );
        return rolUsuarioRepository.findByEmpresa(empresa);
    }

        /**
         * Obtiene los roles activos asociados a un usuario.
         * @param usuario usuario a consultar.
         * @return lista de roles activos del usuario.
         * @throws Exception si ocurre un error durante la consulta.
         */
    @Override
    public List<ResponseRolUsuarioDTO> getRolesUsuarioByUsuario(Usuario usuario) throws Exception {
        return this.rolUsuarioRepository.getByUsuarioAndEstado(usuario, "A").stream().toList();
    }

}

package uq.com.jdq.coresuite.seguridad.rol;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.config.exceptions.RegistroRepetidoException;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio encargado de administrar roles.
 */
@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;
    private final EmpresaRepository empresaRepository;

    /**
     * Registra un nuevo rol validando que no exista otro con el mismo nombre en la empresa.
     * @param createRolDTO datos del rol a crear.
     * @return rol creado.
     * @throws Exception si la empresa no existe o el rol ya esta registrado.
     */
    @Override
    @Transactional
    public ResponseRolDTO createRol(CreateRolDTO createRolDTO) throws Exception {
        Empresa empresa = empresaRepository.findById(createRolDTO.empresaId())
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        
        // Validar que no exista un rol con el mismo nombre en la misma empresa
        Optional<Rol> rolExistente = rolRepository.findByEmpresaAndNombre(empresa, createRolDTO.nombre());
        if(rolExistente.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un rol con el nombre " + createRolDTO.nombre() + " en la empresa");
        }
        
        Rol rol = rolMapper.toEntity(createRolDTO);
        rol.setEmpresa(empresa);
        rol = rolRepository.save(rol);
        return rolMapper.toDTO(rol);
    }

    /**
     * Actualiza un rol existente validando unicidad por empresa y nombre.
     * @param id identificador del rol a actualizar.
     * @param updateRolDTO nuevos datos del rol.
     * @return rol actualizado.
     * @throws Exception si el rol o la empresa no existen, o si el nombre esta repetido.
     */
    @Override
    @Transactional
    public ResponseRolDTO updateRol(Long id, UpdateRolDTO updateRolDTO) throws Exception {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el rol"));
        Empresa empresa = empresaRepository.findById(updateRolDTO.empresaId())
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        
        // Validar que no exista otro rol con el mismo nombre en la misma empresa (excluyendo el actual)
        Optional<Rol> rolExistente = rolRepository.findByEmpresaAndNombreAndIdNot(empresa, updateRolDTO.nombre(), id);
        if(rolExistente.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un rol con el nombre " + updateRolDTO.nombre() + " en la empresa");
        }
        
        rolMapper.updateEntityFromDTO(updateRolDTO, rol);
        rol.setEmpresa(empresa);
        rol = rolRepository.save(rol);
        return rolMapper.toDTO(rol);
    }

    /**
     * Cambia el estado de un rol existente.
     * @param id identificador del rol a inactivar.
     * @param inactiveRolDTO datos del nuevo estado.
     * @return rol actualizado.
     * @throws Exception si el rol no existe.
     */
    @Override
    @Transactional
    public ResponseRolDTO inactiveRol(Long id, InactiveRolDTO inactiveRolDTO) throws Exception {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el rol"));
        rolMapper.inactiveEntityFromDTO(inactiveRolDTO, rol);
        rol = rolRepository.save(rol);
        return rolMapper.toDTO(rol);
    }

    /**
     * Obtiene todos los roles registrados.
     * @return lista de roles.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseRolDTO> getAllRoles() throws Exception {
        return rolRepository.findAll()
                .stream()
                .map(rolMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Consulta un rol por su identificador.
     * @param id identificador del rol.
     * @return rol encontrado.
     * @throws Exception si el rol no existe.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseRolDTO getRolById(Long id) throws Exception {
        return rolRepository.findById(id)
                .map(rolMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe el rol"));
    }

    /**
     * Obtiene los roles registrados para una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de roles asociados.
     * @throws Exception si la empresa no existe.
     */
    @Override
    public List<ResponseRolDTO> getRolsByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        return rolRepository.findByEmpresa(empresa);
    }

}

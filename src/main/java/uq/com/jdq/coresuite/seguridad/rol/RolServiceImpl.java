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

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;
    private final EmpresaRepository empresaRepository;

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

    @Override
    @Transactional
    public ResponseRolDTO inactiveRol(Long id, InactiveRolDTO inactiveRolDTO) throws Exception {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el rol"));
        rolMapper.inactiveEntityFromDTO(inactiveRolDTO, rol);
        rol = rolRepository.save(rol);
        return rolMapper.toDTO(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseRolDTO> getAllRoles() throws Exception {
        return rolRepository.findAll()
                .stream()
                .map(rolMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseRolDTO getRolById(Long id) throws Exception {
        return rolRepository.findById(id)
                .map(rolMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe el rol"));
    }

    @Override
    public List<ResponseRolDTO> getRolsByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        return rolRepository.findByEmpresa(empresa);
    }

}

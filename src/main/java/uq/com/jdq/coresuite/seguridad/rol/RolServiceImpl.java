package uq.com.jdq.coresuite.seguridad.rol;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;
    private final EmpresaRepository empresaRepository;

    @Override
    @Transactional
    public ResponseRolDTO createRol(CreateRolDTO createRolDTO) {
        Empresa empresa = empresaRepository.findById(createRolDTO.empresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        Rol rol = rolMapper.toEntity(createRolDTO);
        rol.setEmpresa(empresa);
        rol = rolRepository.save(rol);
        return rolMapper.toDTO(rol);
    }

    @Override
    @Transactional
    public ResponseRolDTO updateRol(Long id, UpdateRolDTO updateRolDTO) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        Empresa empresa = empresaRepository.findById(updateRolDTO.empresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        rolMapper.updateEntityFromDTO(updateRolDTO, rol);
        rol.setEmpresa(empresa);
        rol = rolRepository.save(rol);
        return rolMapper.toDTO(rol);
    }

    @Override
    @Transactional
    public ResponseRolDTO inactiveRol(Long id, InactiveRolDTO inactiveRolDTO) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        rolMapper.inactiveEntityFromDTO(inactiveRolDTO, rol);
        rol = rolRepository.save(rol);
        return rolMapper.toDTO(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseRolDTO> getAllRoles() {
        return rolRepository.findAll()
                .stream()
                .map(rolMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseRolDTO getRolById(Long id) {
        return rolRepository.findById(id)
                .map(rolMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<ResponseRolDTO> getRolsByEmpresa(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        return rolRepository.findByEmpresa(empresa);
    }

}

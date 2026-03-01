package uq.com.jdq.coresuite.sistema.sesion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.seguridad.usuario.UsuarioRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SesionServiceImpl implements SesionService {

    private final SesionRepository sesionRepository;
    private final SesionMapper sesionMapper;
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public ResponseSesionDTO createSesion(CreateSesionDTO createSesionDTO) {
        Sesion sesion = sesionMapper.toEntity(createSesionDTO);
        Long empresaId = createSesionDTO.empresaId();
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        Long usuarioId = createSesionDTO.usuarioId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("No existe el usuario")
        );
        sesion.setEmpresa(empresa);
        sesion.setUsuario(usuario);
        sesion = sesionRepository.save(sesion);
        return sesionMapper.toDTO(sesion);
    }

    @Override
    @Transactional
    public ResponseSesionDTO updateSesion(Long id, UpdateSesionDTO updateSesionDTO) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesion not found"));
        sesionMapper.updateEntityFromDTO(updateSesionDTO, sesion);
        sesion = sesionRepository.save(sesion);
        return sesionMapper.toDTO(sesion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseSesionDTO> getAllSesiones() {
        return sesionRepository.findAll().stream()
                .map(sesionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseSesionDTO getSesionById(Long id) {
        return sesionRepository.findById(id)
                .map(sesionMapper::toDTO)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseSesionDTO> getSesionesByEmpresa(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(
                () -> new RuntimeException("Empresa not found")
        );
        return sesionRepository.findByEmpresa(empresa);
    }

}

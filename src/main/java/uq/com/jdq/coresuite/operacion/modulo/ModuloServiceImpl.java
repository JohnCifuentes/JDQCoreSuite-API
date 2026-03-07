package uq.com.jdq.coresuite.operacion.modulo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuloServiceImpl implements ModuloService {

    private final ModuloRepository moduloRepository;
    private final ModuloMapper moduloMapper;
    private final EmpresaRepository empresaRepository;

    @Override
    @Transactional
    public ResponseModuloDTO createModulo(CreateModuloDTO createModuloDTO) throws Exception {
        Empresa empresa = empresaRepository.findById(createModuloDTO.empresaId())
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        Modulo modulo = moduloMapper.toEntity(createModuloDTO);
        modulo.setEmpresa(empresa);
        modulo = moduloRepository.save(modulo);
        return moduloMapper.toDTO(modulo);
    }

    @Override
    @Transactional
    public ResponseModuloDTO updateModulo(Long id, UpdateModuloDTO updateModuloDTO) throws Exception {
        Empresa empresa = empresaRepository.findById(updateModuloDTO.empresaId())
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        Modulo modulo = moduloRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el modulo"));
        moduloMapper.updateEntityFromDTO(updateModuloDTO, modulo);
        modulo.setEmpresa(empresa);
        modulo = moduloRepository.save(modulo);
        return moduloMapper.toDTO(modulo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseModuloDTO> getAllModulos() throws Exception {
        return moduloRepository.findAll().stream().map(moduloMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseModuloDTO getModuloById(Long id) throws Exception {
        return moduloRepository.findById(id)
                .map(moduloMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe el modulo"));
    }

    @Override
    public List<ResponseModuloDTO> getModulosByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        return moduloRepository.findByEmpresa(empresa);
    }

}

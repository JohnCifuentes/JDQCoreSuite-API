package uq.com.jdq.coresuite.sistema.empresa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.catalogo.departamento.Departamento;
import uq.com.jdq.coresuite.catalogo.departamento.DepartamentoRepository;
import uq.com.jdq.coresuite.catalogo.municipio.Municipio;
import uq.com.jdq.coresuite.catalogo.municipio.MunicipioRepository;
import uq.com.jdq.coresuite.catalogo.pais.Pais;
import uq.com.jdq.coresuite.catalogo.pais.PaisRepository;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacion;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;
    private final TipoIdentificacionRepository tipoIdentificacionRepository;
    private final PaisRepository paisRepository;
    private final DepartamentoRepository departamentoRepository;
    private final MunicipioRepository municipioRepository;

    @Override
    @Transactional
    public ResponseEmpresaDTO createEmpresa(CreateEmpresaDTO createEmpresaDTO) throws Exception {
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(createEmpresaDTO.tipoIdentificacionId())
                .orElseThrow(() -> new RuntimeException("Tipo de Identificacion no encontrado"));
        Pais pais = paisRepository.findById(createEmpresaDTO.paisId())
                .orElseThrow(() -> new RuntimeException("Pais no encontrado"));
        Departamento departamento = departamentoRepository.findById(createEmpresaDTO.departamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
        Municipio municipio = municipioRepository.findById(createEmpresaDTO.municipioId())
                .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));
        Empresa empresa = empresaMapper.toEntity(createEmpresaDTO);
        empresa.setTipoIdentificacion(tipoIdentificacion);
        empresa.setPais(pais);
        empresa.setDepartamento(departamento);
        empresa.setMunicipio(municipio);
        empresa = empresaRepository.save(empresa);
        return empresaMapper.toDTO(empresa);
    }

    @Override
    @Transactional
    public ResponseEmpresaDTO updateEmpresa(Long id, UpdateEmpresaDTO updateEmpresaDTO) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(updateEmpresaDTO.tipoIdentificacionId())
                .orElseThrow(() -> new RuntimeException("Tipo de Identificacion no encontrado"));
        Pais pais = paisRepository.findById(updateEmpresaDTO.paisId())
                .orElseThrow(() -> new RuntimeException("Pais no encontrado"));
        Departamento departamento = departamentoRepository.findById(updateEmpresaDTO.departamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
        Municipio municipio = municipioRepository.findById(updateEmpresaDTO.municipioId())
                .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));
        empresaMapper.updateEntityFromDTO(updateEmpresaDTO, empresa);
        empresa.setTipoIdentificacion(tipoIdentificacion);
        empresa.setPais(pais);
        empresa.setDepartamento(departamento);
        empresa.setMunicipio(municipio);
        empresa = empresaRepository.save(empresa);
        return empresaMapper.toDTO(empresa);
    }

    @Override
    @Transactional
    public ResponseEmpresaDTO inactiveEmpresa(Long id, InactiveEmpresaDTO inactiveEmpresaDTO) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        empresaMapper.inactiveEntityFromDTO(inactiveEmpresaDTO, empresa);
        empresa = empresaRepository.save(empresa);
        return empresaMapper.toDTO(empresa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseEmpresaDTO> getAllEmpresas() {
        return empresaRepository.findAll().stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEmpresaDTO getEmpresaById(Long id) {
        return empresaRepository.findById(id)
                .map(empresaMapper::toDTO)
                .orElse(null);
    }

}

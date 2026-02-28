package uq.com.jdq.coresuite.catalogo.departamento;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import uq.com.jdq.coresuite.catalogo.pais.Pais;
import uq.com.jdq.coresuite.catalogo.pais.PaisRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartamentoServicioImp implements DepartamentoService {
    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;
    private final PaisRepository paisRepository;

    @Override
    public List<DepartamentoDTO> getAllDepartamentos() throws Exception {
        return departamentoRepository.findAll().stream().map(departamentoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<DepartamentoDTO> getAllDepartamentosByPais(Long paisId) throws Exception {
        Pais pais = paisRepository.findById(paisId)
                .orElseThrow(() -> new Exception("No se encontro el país"));
        return departamentoRepository.findByPais(pais);
    }

    @Override
    public DepartamentoDTO getDepartamentoById(@Valid @RequestParam Long id) throws Exception {
        Departamento departamento = departamentoRepository.findById(id).orElseThrow(() -> new Exception("Departamento no encontrado"));
        return departamentoMapper.toDTO(departamento);
    }

}

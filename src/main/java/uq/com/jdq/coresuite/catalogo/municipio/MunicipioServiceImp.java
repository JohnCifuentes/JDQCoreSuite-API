package uq.com.jdq.coresuite.catalogo.municipio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uq.com.jdq.coresuite.catalogo.departamento.Departamento;
import uq.com.jdq.coresuite.catalogo.departamento.DepartamentoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MunicipioServiceImp implements MunicipioService {
    private final MunicipioRepository municipioRepository;
    private final MunicipioMapper municipioMapper;
    private final DepartamentoRepository departamentoRepository;

    @Override
    public List<MunicipioDTO> getAllMunicipios() throws Exception {
        return municipioRepository.findAll()
                .stream()
                .map(municipioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MunicipioDTO> getAllMunicipiosByDepartamento(Long departamentoId) throws Exception {
        Departamento departamento = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new Exception("Departamento no encontrado"));
        return municipioRepository.findByDepartamento(departamento);
    }

    @Override
    public MunicipioDTO getMunicipioById(Long id) throws Exception {
        Municipio municipio = municipioRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el genero"));
        return municipioMapper.toDto(municipio);
    }
}

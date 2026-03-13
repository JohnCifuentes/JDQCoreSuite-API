package uq.com.jdq.coresuite.catalogo.municipio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uq.com.jdq.coresuite.catalogo.departamento.Departamento;
import uq.com.jdq.coresuite.catalogo.departamento.DepartamentoRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio de consulta de municipios.
 */
@Service
@RequiredArgsConstructor
public class MunicipioServiceImp implements MunicipioService {
    private final MunicipioRepository municipioRepository;
    private final MunicipioMapper municipioMapper;
    private final DepartamentoRepository departamentoRepository;

    /**
     * Obtiene la lista completa de municipios.
     * @return lista de municipios.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public List<MunicipioDTO> getAllMunicipios() throws Exception {
        return municipioRepository.findAll()
                .stream()
                .map(municipioMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene los municipios de un departamento.
     * @param departamentoId identificador del departamento.
     * @return lista de municipios del departamento.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public List<MunicipioDTO> getAllMunicipiosByDepartamento(Long departamentoId) throws Exception {
        Departamento departamento = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new Exception("Departamento no encontrado"));
        return municipioRepository.findByDepartamento(departamento);
    }

    /**
     * Obtiene un municipio por identificador.
     * @param id identificador del municipio.
     * @return municipio encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public MunicipioDTO getMunicipioById(Long id) throws Exception {
        Municipio municipio = municipioRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el departamento"));
        return municipioMapper.toDto(municipio);
    }

    /**
     * Obtiene la entidad Municipio asociada a un identificador.
     * @param id identificador del municipio.
     * @return entidad municipio.
     * @throws Exception si ocurre un error durante la consulta.
     */
    public Municipio getMunicipio(Long id) throws Exception {
        return municipioMapper.toEntity(this.getMunicipioById(id));
    }

}

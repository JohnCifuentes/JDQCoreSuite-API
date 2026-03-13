package uq.com.jdq.coresuite.catalogo.departamento;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uq.com.jdq.coresuite.catalogo.pais.Pais;
import uq.com.jdq.coresuite.catalogo.pais.PaisRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio de consulta de departamentos.
 */
@Service
@RequiredArgsConstructor
public class DepartamentoServiceImp implements DepartamentoService {
    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;
    private final PaisRepository paisRepository;

    /**
     * Obtiene la lista completa de departamentos.
     * @return lista de departamentos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public List<DepartamentoDTO> getAllDepartamentos() throws Exception {
        return departamentoRepository.findAll().stream().map(departamentoMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Obtiene los departamentos de un pais.
     * @param paisId identificador del pais.
     * @return lista de departamentos del pais.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public List<DepartamentoDTO> getAllDepartamentosByPais(Long paisId) throws Exception {
        Pais pais = paisRepository.findById(paisId)
                .orElseThrow(() -> new Exception("No se encontro el paÃ­s"));
        return departamentoRepository.findByPais(pais);
    }

    /**
     * Obtiene un departamento por identificador.
     * @param id identificador del departamento.
     * @return departamento encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public DepartamentoDTO getDepartamentoById(Long id) throws Exception {
        Departamento departamento = departamentoRepository.findById(id).orElseThrow(() -> new Exception("Departamento no encontrado"));
        return departamentoMapper.toDTO(departamento);
    }

    /**
     * Obtiene la entidad Departamento asociada a un identificador.
     * @param id identificador del departamento.
     * @return entidad departamento.
     * @throws Exception si ocurre un error durante la consulta.
     */
    public Departamento getDepartamento(Long id) throws Exception {
        return departamentoMapper.toEntity(this.getDepartamentoById(id));
    }

}

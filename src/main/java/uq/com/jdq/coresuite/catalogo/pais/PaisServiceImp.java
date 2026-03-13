package uq.com.jdq.coresuite.catalogo.pais;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio de consulta de paises.
 */
@Service
@RequiredArgsConstructor
public class PaisServiceImp implements PaisService {
    private final PaisRepository paisRepository;
    private final PaisMapper paisMapper;

    /**
     * Obtiene la lista completa de paises.
     * @return lista de paises.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public List<PaisDTO> getAllPaises() throws Exception {
        return paisRepository.findAll()
                .stream()
                .map(paisMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un pais por identificador.
     * @param id identificador del pais.
     * @return pais encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public PaisDTO getPaisById(Long id) throws Exception{
        Pais pais = paisRepository.findById(id)
                .orElseThrow(() -> new Exception("Pais no encontrado"));
        return paisMapper.toDto(pais);
    }

    /**
     * Obtiene la entidad Pais asociada a un identificador.
     * @param id identificador del pais.
     * @return entidad pais.
     * @throws Exception si ocurre un error durante la consulta.
     */
    public Pais getPais(Long id) throws Exception{
        return paisMapper.toEntity(this.getPaisById(id));
    }

}

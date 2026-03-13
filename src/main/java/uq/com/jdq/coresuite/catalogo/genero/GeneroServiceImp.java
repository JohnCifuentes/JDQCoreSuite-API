package uq.com.jdq.coresuite.catalogo.genero;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio de consulta de generos.
 */
@Service
@RequiredArgsConstructor
public class GeneroServiceImp implements GeneroService{
    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;

    /**
     * Obtiene la lista completa de generos.
     * @return lista de generos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public List<GeneroDTO> getAllGeneros() throws Exception {
        return generoRepository.findAll().stream().map(generoMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Obtiene un genero por identificador.
     * @param id identificador del genero.
     * @return genero encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public GeneroDTO getGeneroById(Long id) throws Exception {
        Genero genero = generoRepository.findById(id).orElseThrow(() -> new Exception("No se encontro el genero"));
        return generoMapper.toDTO(genero);
    }
}

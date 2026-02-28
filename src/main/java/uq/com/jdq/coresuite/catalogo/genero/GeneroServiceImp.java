package uq.com.jdq.coresuite.catalogo.genero;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneroServiceImp implements GeneroService{
    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;

    @Override
    public List<GeneroDTO> getAllGeneros() throws Exception {
        return generoRepository.findAll().stream().map(generoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public GeneroDTO getGeneroById(Long id) throws Exception {
        Genero genero = generoRepository.findById(id).orElseThrow(() -> new Exception("No se encontro el genero"));
        return generoMapper.toDTO(genero);
    }
}

package uq.com.jdq.coresuite.catalogo.pais;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaisServiceImp implements PaisService {
    private final PaisRepository paisRepository;
    private final PaisMapper paisMapper;

    @Override
    public List<PaisDTO> getAllPaises() throws Exception {
        return paisRepository.findAll()
                .stream()
                .map(paisMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaisDTO getPaisById(Long id) throws Exception {
        Pais pais = paisRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el genero"));
        return paisMapper.toDto(pais);
    }
}

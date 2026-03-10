package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.config.exceptions.RegistroRepetidoException;
import uq.com.jdq.coresuite.operacion.interfaz.Interfaz;
import uq.com.jdq.coresuite.operacion.interfaz.InterfazRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterfaceGrupoCamposServiceImpl implements InterfaceGrupoCamposService {

    private final InterfaceGrupoCamposRepository interfaceGrupoCamposRepository;
    private final InterfaceGrupoCamposMapper interfaceGrupoCamposMapper;
    private final InterfazRepository interfazRepository;

    @Override
    @Transactional
    public ResponseInterfaceGrupoCamposDTO createInterfaceGrupoCampos(CreateInterfaceGrupoCamposDTO createInterfaceGrupoCamposDTO) throws Exception {
        InterfaceGrupoCampos interfaceGrupoCampos = interfaceGrupoCamposMapper.toEntity(createInterfaceGrupoCamposDTO);
        
        Optional<Interfaz> interfaz = interfazRepository.findById(createInterfaceGrupoCamposDTO.interfazId());
        if(interfaz.isEmpty()) {
            throw new NoExisteException("No existe la interfaz");
        }
        
        // Validar que no exista un grupo de campos con el mismo nombre en la misma interfaz
        Optional<InterfaceGrupoCampos> grupoExistenteNombre = interfaceGrupoCamposRepository.findByInterfazAndNombre(interfaz.get(), createInterfaceGrupoCamposDTO.nombre());
        if(grupoExistenteNombre.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un grupo de campos con el nombre " + createInterfaceGrupoCamposDTO.nombre() + " en la interfaz");
        }
        
        // Validar que no exista un grupo de campos con el mismo índice en la misma interfaz
        Optional<InterfaceGrupoCampos> grupoExistenteIndice = interfaceGrupoCamposRepository.findByInterfazAndIndice(interfaz.get(), createInterfaceGrupoCamposDTO.indice());
        if(grupoExistenteIndice.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un grupo de campos con el índice " + createInterfaceGrupoCamposDTO.indice() + " en la interfaz");
        }
        
        interfaceGrupoCampos.setInterfaz(interfaz.get());
        interfaceGrupoCampos = interfaceGrupoCamposRepository.save(interfaceGrupoCampos);
        return interfaceGrupoCamposMapper.toDTO(interfaceGrupoCampos);
    }

    @Override
    @Transactional
    public ResponseInterfaceGrupoCamposDTO updateInterfaceGrupoCampos(Long id, UpdateInterfaceGrupoCamposDTO updateInterfaceGrupoCamposDTO) throws Exception {
        Optional<Interfaz> interfaz = interfazRepository.findById(updateInterfaceGrupoCamposDTO.interfazId());
        if(interfaz.isEmpty()) {
            throw new NoExisteException("No existe la interfaz");
        }
        
        Optional<InterfaceGrupoCampos> interfaceGrupoCampos = interfaceGrupoCamposRepository.findById(id);
        if(interfaceGrupoCampos.isEmpty()) {
            throw new NoExisteException("No existe el grupo de campos");
        }
        
        // Validar que no exista otro grupo de campos con el mismo nombre en la misma interfaz (excluyendo el actual)
        Optional<InterfaceGrupoCampos> grupoExistenteNombre = interfaceGrupoCamposRepository.findByInterfazAndNombreAndIdNot(interfaz.get(), updateInterfaceGrupoCamposDTO.nombre(), id);
        if(grupoExistenteNombre.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un grupo de campos con el nombre " + updateInterfaceGrupoCamposDTO.nombre() + " en la interfaz");
        }
        
        // Validar que no exista otro grupo de campos con el mismo índice en la misma interfaz (excluyendo el actual)
        Optional<InterfaceGrupoCampos> grupoExistenteIndice = interfaceGrupoCamposRepository.findByInterfazAndIndiceAndIdNot(interfaz.get(), updateInterfaceGrupoCamposDTO.indice(), id);
        if(grupoExistenteIndice.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un grupo de campos con el índice " + updateInterfaceGrupoCamposDTO.indice() + " en la interfaz");
        }
        
        InterfaceGrupoCampos interfaceGrupoCamposAux = interfaceGrupoCampos.get();
        interfaceGrupoCamposMapper.updateEntityFromDTO(updateInterfaceGrupoCamposDTO, interfaceGrupoCamposAux);
        interfaceGrupoCamposAux.setInterfaz(interfaz.get());
        interfaceGrupoCamposAux = interfaceGrupoCamposRepository.save(interfaceGrupoCamposAux);
        return interfaceGrupoCamposMapper.toDTO(interfaceGrupoCamposAux);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseInterfaceGrupoCamposDTO> getAllInterfaceGrupoCampos() {
        return interfaceGrupoCamposRepository.findAll().stream()
                .map(interfaceGrupoCamposMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseInterfaceGrupoCamposDTO getInterfaceGrupoCamposById(Long id) throws Exception {
        Optional<InterfaceGrupoCampos> interfaceGrupoCampos = interfaceGrupoCamposRepository.findById(id);
        if(interfaceGrupoCampos.isEmpty()) {
            throw new NoExisteException("No existe el grupo de campos");
        }
        return interfaceGrupoCamposMapper.toDTO(interfaceGrupoCampos.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseInterfaceGrupoCamposDTO> getInterfaceGrupoCamposByInterfaz(Long interfazId) throws Exception {
        Interfaz interfaz = interfazRepository.findById(interfazId).orElseThrow(() ->
                new NoExisteException("No existe la interfaz")
        );
        return interfaceGrupoCamposRepository.findByInterfaz(interfaz);
    }

}

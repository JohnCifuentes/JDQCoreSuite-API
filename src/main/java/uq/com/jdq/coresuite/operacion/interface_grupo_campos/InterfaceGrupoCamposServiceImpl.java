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

/**
 * Implementacion del servicio de administracion y consulta de grupos de campos por interfaz.
 */
@Service
@RequiredArgsConstructor
public class InterfaceGrupoCamposServiceImpl implements InterfaceGrupoCamposService {

    private final InterfaceGrupoCamposRepository interfaceGrupoCamposRepository;
    private final InterfaceGrupoCamposMapper interfaceGrupoCamposMapper;
    private final InterfazRepository interfazRepository;

    /**
     * Crea un nuevo grupo de campos asociado a una interfaz.
     * @param createInterfaceGrupoCamposDTO datos de creacion.
     * @return grupo de campos creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
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
        
        // Validar que no exista un grupo de campos con el mismo Ã­ndice en la misma interfaz
        Optional<InterfaceGrupoCampos> grupoExistenteIndice = interfaceGrupoCamposRepository.findByInterfazAndIndice(interfaz.get(), createInterfaceGrupoCamposDTO.indice());
        if(grupoExistenteIndice.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un grupo de campos con el Ã­ndice " + createInterfaceGrupoCamposDTO.indice() + " en la interfaz");
        }
        
        interfaceGrupoCampos.setInterfaz(interfaz.get());
        interfaceGrupoCampos = interfaceGrupoCamposRepository.save(interfaceGrupoCampos);
        return interfaceGrupoCamposMapper.toDTO(interfaceGrupoCampos);
    }

    /**
     * Actualiza un grupo de campos existente.
     * @param id identificador del grupo de campos.
     * @param updateInterfaceGrupoCamposDTO datos actualizados.
     * @return grupo de campos actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
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
        
        // Validar que no exista otro grupo de campos con el mismo Ã­ndice en la misma interfaz (excluyendo el actual)
        Optional<InterfaceGrupoCampos> grupoExistenteIndice = interfaceGrupoCamposRepository.findByInterfazAndIndiceAndIdNot(interfaz.get(), updateInterfaceGrupoCamposDTO.indice(), id);
        if(grupoExistenteIndice.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un grupo de campos con el Ã­ndice " + updateInterfaceGrupoCamposDTO.indice() + " en la interfaz");
        }
        
        InterfaceGrupoCampos interfaceGrupoCamposAux = interfaceGrupoCampos.get();
        interfaceGrupoCamposMapper.updateEntityFromDTO(updateInterfaceGrupoCamposDTO, interfaceGrupoCamposAux);
        interfaceGrupoCamposAux.setInterfaz(interfaz.get());
        interfaceGrupoCamposAux = interfaceGrupoCamposRepository.save(interfaceGrupoCamposAux);
        return interfaceGrupoCamposMapper.toDTO(interfaceGrupoCamposAux);
    }

    /**
     * Obtiene la lista completa de grupos de campos.
     * @return lista de grupos de campos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseInterfaceGrupoCamposDTO> getAllInterfaceGrupoCampos() {
        return interfaceGrupoCamposRepository.findAll().stream()
                .map(interfaceGrupoCamposMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un grupo de campos por identificador.
     * @param id identificador del grupo de campos.
     * @return grupo de campos encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseInterfaceGrupoCamposDTO getInterfaceGrupoCamposById(Long id) throws Exception {
        Optional<InterfaceGrupoCampos> interfaceGrupoCampos = interfaceGrupoCamposRepository.findById(id);
        if(interfaceGrupoCampos.isEmpty()) {
            throw new NoExisteException("No existe el grupo de campos");
        }
        return interfaceGrupoCamposMapper.toDTO(interfaceGrupoCampos.get());
    }

    /**
     * Obtiene los grupos de campos asociados a una interfaz.
     * @param interfazId identificador de la interfaz.
     * @return lista de grupos de campos relacionadas.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseInterfaceGrupoCamposDTO> getInterfaceGrupoCamposByInterfaz(Long interfazId) throws Exception {
        Interfaz interfaz = interfazRepository.findById(interfazId).orElseThrow(() ->
                new NoExisteException("No existe la interfaz")
        );
        return interfaceGrupoCamposRepository.findByInterfaz(interfaz);
    }

}

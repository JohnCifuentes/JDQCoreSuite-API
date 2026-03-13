package uq.com.jdq.coresuite.operacion.interfaz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.config.exceptions.RegistroRepetidoException;
import uq.com.jdq.coresuite.operacion.modulo.Modulo;
import uq.com.jdq.coresuite.operacion.modulo.ModuloRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio de administracion y consulta de interfaces.
 */
@Service
@RequiredArgsConstructor
public class InterfazServiceImpl implements InterfazService {

    private final InterfazRepository interfazRepository;
    private final InterfazMapper interfazMapper;
    private final ModuloRepository moduloRepository;

    /**
     * Crea una nueva interfaz.
     * @param createInterfazDTO datos de creacion.
     * @return interfaz creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @Override
    @Transactional
    public ResponseInterfazDTO createInterfaz(CreateInterfazDTO createInterfazDTO) throws Exception {
        Interfaz interfaz = interfazMapper.toEntity(createInterfazDTO);
        
        Optional<Modulo> modulo = moduloRepository.findById(createInterfazDTO.moduloId());
        if(modulo.isEmpty()) {
            throw new NoExisteException("No existe el mÃ³dulo");
        }
        
        // Validar que no exista una interfaz con el mismo nombre en el mismo mÃ³dulo
        Optional<Interfaz> interfazExistenteNombre = interfazRepository.findByModuloAndNombre(modulo.get(), createInterfazDTO.nombre());
        if(interfazExistenteNombre.isPresent()) {
            throw new RegistroRepetidoException("Ya existe una interfaz con el nombre " + createInterfazDTO.nombre() + " en el mÃ³dulo");
        }
        
        // Validar que no exista una interfaz con el mismo Ã­ndice en el mismo mÃ³dulo
        Optional<Interfaz> interfazExistenteIndice = interfazRepository.findByModuloAndIndice(modulo.get(), createInterfazDTO.indice());
        if(interfazExistenteIndice.isPresent()) {
            throw new RegistroRepetidoException("Ya existe una interfaz con el Ã­ndice " + createInterfazDTO.indice() + " en el mÃ³dulo");
        }
        
        interfaz.setModulo(modulo.get());
        interfaz = interfazRepository.save(interfaz);
        return interfazMapper.toDTO(interfaz);
    }

    /**
     * Actualiza una interfaz existente.
     * @param id identificador de la interfaz.
     * @param updateInterfazDTO datos actualizados.
     * @return interfaz actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @Override
    @Transactional
    public ResponseInterfazDTO updateInterfaz(Long id, UpdateInterfazDTO updateInterfazDTO) throws Exception {
        Optional<Modulo> modulo = moduloRepository.findById(updateInterfazDTO.moduloId());
        if(modulo.isEmpty()) {
            throw new NoExisteException("No existe el mÃ³dulo");
        }
        
        Optional<Interfaz> interfaz = interfazRepository.findById(id);
        if(interfaz.isEmpty()) {
            throw new NoExisteException("No existe la interfaz");
        }
        
        // Validar que no exista otra interfaz con el mismo nombre en el mismo mÃ³dulo (excluyendo la actual)
        Optional<Interfaz> interfazExistenteNombre = interfazRepository.findByModuloAndNombreAndIdNot(modulo.get(), updateInterfazDTO.nombre(), id);
        if(interfazExistenteNombre.isPresent()) {
            throw new RegistroRepetidoException("Ya existe una interfaz con el nombre " + updateInterfazDTO.nombre() + " en el mÃ³dulo");
        }
        
        // Validar que no exista otra interfaz con el mismo Ã­ndice en el mismo mÃ³dulo (excluyendo la actual)
        Optional<Interfaz> interfazExistenteIndice = interfazRepository.findByModuloAndIndiceAndIdNot(modulo.get(), updateInterfazDTO.indice(), id);
        if(interfazExistenteIndice.isPresent()) {
            throw new RegistroRepetidoException("Ya existe una interfaz con el Ã­ndice " + updateInterfazDTO.indice() + " en el mÃ³dulo");
        }
        
        Interfaz interfazAux = interfaz.get();
        interfazMapper.updateEntityFromDTO(updateInterfazDTO, interfazAux);
        interfazAux.setModulo(modulo.get());
        interfazAux = interfazRepository.save(interfazAux);
        return interfazMapper.toDTO(interfazAux);
    }

    /**
     * Obtiene la lista completa de interfaces.
     * @return lista de interfaces.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseInterfazDTO> getAllInterfaz() {
        return interfazRepository.findAll().stream()
                .map(interfazMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una interfaz por identificador.
     * @param id identificador de la interfaz.
     * @return interfaz encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseInterfazDTO getInterfazById(Long id) throws Exception {
        Optional<Interfaz> interfaz = interfazRepository.findById(id);
        if(interfaz.isEmpty()) {
            throw new NoExisteException("No existe la interfaz");
        }
        return interfazMapper.toDTO(interfaz.get());
    }

    /**
     * Obtiene las interfaces asociadas a un modulo.
     * @param moduloId identificador del modulo.
     * @return lista de interfaces relacionadas.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseInterfazDTO> getInterfazByModulo(Long moduloId) throws Exception {
        Modulo modulo = moduloRepository.findById(moduloId).orElseThrow(() ->
                new NoExisteException("No existe el mÃ³dulo")
        );
        return interfazRepository.findByModulo(modulo);
    }

}

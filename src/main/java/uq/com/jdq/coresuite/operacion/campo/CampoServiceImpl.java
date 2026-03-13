package uq.com.jdq.coresuite.operacion.campo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.config.exceptions.RegistroRepetidoException;
import uq.com.jdq.coresuite.operacion.interfaz.Interfaz;
import uq.com.jdq.coresuite.operacion.interfaz.InterfazRepository;
import uq.com.jdq.coresuite.operacion.interface_grupo_campos.InterfaceGrupoCampos;
import uq.com.jdq.coresuite.operacion.interface_grupo_campos.InterfaceGrupoCamposRepository;
import uq.com.jdq.coresuite.operacion.tipo_campo.TipoCampo;
import uq.com.jdq.coresuite.operacion.tipo_campo.TipoCampoRepository;
import uq.com.jdq.coresuite.operacion.lista_valores.ListaValores;
import uq.com.jdq.coresuite.operacion.lista_valores.ListaValoresRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio de administracion y consulta de campos.
 */
@Service
@RequiredArgsConstructor
public class CampoServiceImpl implements CampoService {

    private final CampoRepository campoRepository;
    private final CampoMapper campoMapper;
    private final InterfazRepository interfazRepository;
    private final InterfaceGrupoCamposRepository interfaceGrupoCamposRepository;
    private final TipoCampoRepository tipoCampoRepository;
    private final ListaValoresRepository listaValoresRepository;

    /**
     * Crea un nuevo campo.
     * @param createCampoDTO datos de creacion del campo.
     * @return campo creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @Override
    @Transactional
    public ResponseCampoDTO createCampo(CreateCampoDTO createCampoDTO) throws Exception {
        Campo campo = campoMapper.toEntity(createCampoDTO);
        
        Optional<Interfaz> interfaz = interfazRepository.findById(createCampoDTO.interfazId());
        if(interfaz.isEmpty()) {
            throw new NoExisteException("No existe la interfaz");
        }
        
        // Validar que no exista un campo con el mismo nombre en la misma interfaz
        Optional<Campo> campoExistenteNombre = campoRepository.findByInterfazAndNombre(interfaz.get(), createCampoDTO.nombre());
        if(campoExistenteNombre.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un campo con el nombre " + createCampoDTO.nombre() + " en la interfaz");
        }
        
        // Validar que no exista un campo con el mismo Ã­ndice en la misma interfaz
        Optional<Campo> campoExistenteIndice = campoRepository.findByInterfazAndIndice(interfaz.get(), createCampoDTO.indice());
        if(campoExistenteIndice.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un campo con el Ã­ndice " + createCampoDTO.indice() + " en la interfaz");
        }
        
        if(createCampoDTO.interfaceGrupoCamposId() != null) {
            Optional<InterfaceGrupoCampos> interfaceGrupoCampos = interfaceGrupoCamposRepository.findById(createCampoDTO.interfaceGrupoCamposId());
            if(interfaceGrupoCampos.isEmpty()) {
                throw new NoExisteException("No existe el grupo de campos");
            }
            campo.setInterfaceGrupoCampos(interfaceGrupoCampos.get());
        }
        
        Optional<TipoCampo> tipoCampo = tipoCampoRepository.findById(createCampoDTO.tipoCampoId());
        if(tipoCampo.isEmpty()) {
            throw new NoExisteException("No existe el tipo de campo");
        }
        
        if(createCampoDTO.listaValoresId() != null) {
            Optional<ListaValores> listaValores = listaValoresRepository.findById(createCampoDTO.listaValoresId());
            if(listaValores.isEmpty()) {
                throw new NoExisteException("No existe la lista de valores");
            }
            campo.setListaValores(listaValores.get());
        }
        
        campo.setInterfaz(interfaz.get());
        campo.setTipoCampo(tipoCampo.get());
        campo = campoRepository.save(campo);
        return campoMapper.toDTO(campo);
    }

    /**
     * Actualiza un campo existente.
     * @param id identificador del campo.
     * @param updateCampoDTO datos actualizados del campo.
     * @return campo actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @Override
    @Transactional
    public ResponseCampoDTO updateCampo(Long id, UpdateCampoDTO updateCampoDTO) throws Exception {
        Optional<Interfaz> interfaz = interfazRepository.findById(updateCampoDTO.interfazId());
        if(interfaz.isEmpty()) {
            throw new NoExisteException("No existe la interfaz");
        }
        
        // Validar que no exista otro campo con el mismo nombre en la misma interfaz (excluyendo el actual)
        Optional<Campo> campoExistenteNombre = campoRepository.findByInterfazAndNombreAndIdNot(interfaz.get(), updateCampoDTO.nombre(), id);
        if(campoExistenteNombre.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un campo con el nombre " + updateCampoDTO.nombre() + " en la interfaz");
        }
        
        // Validar que no exista otro campo con el mismo Ã­ndice en la misma interfaz (excluyendo el actual)
        Optional<Campo> campoExistenteIndice = campoRepository.findByInterfazAndIndiceAndIdNot(interfaz.get(), updateCampoDTO.indice(), id);
        if(campoExistenteIndice.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un campo con el Ã­ndice " + updateCampoDTO.indice() + " en la interfaz");
        }
        
        if(updateCampoDTO.interfaceGrupoCamposId() != null) {
            Optional<InterfaceGrupoCampos> interfaceGrupoCampos = interfaceGrupoCamposRepository.findById(updateCampoDTO.interfaceGrupoCamposId());
            if(interfaceGrupoCampos.isEmpty()) {
                throw new NoExisteException("No existe el grupo de campos");
            }
        }
        
        Optional<TipoCampo> tipoCampo = tipoCampoRepository.findById(updateCampoDTO.tipoCampoId());
        if(tipoCampo.isEmpty()) {
            throw new NoExisteException("No existe el tipo de campo");
        }
        
        if(updateCampoDTO.listaValoresId() != null) {
            Optional<ListaValores> listaValores = listaValoresRepository.findById(updateCampoDTO.listaValoresId());
            if(listaValores.isEmpty()) {
                throw new NoExisteException("No existe la lista de valores");
            }
        }
        
        Optional<Campo> campo = campoRepository.findById(id);
        if(campo.isEmpty()) {
            throw new NoExisteException("No existe el campo");
        }
        
        Campo campoAux = campo.get();
        campoMapper.updateEntityFromDTO(updateCampoDTO, campoAux);
        campoAux.setInterfaz(interfaz.get());
        campoAux.setTipoCampo(tipoCampo.get());
        
        if(updateCampoDTO.interfaceGrupoCamposId() != null) {
            InterfaceGrupoCampos interfaceGrupoCampos = interfaceGrupoCamposRepository.findById(updateCampoDTO.interfaceGrupoCamposId()).get();
            campoAux.setInterfaceGrupoCampos(interfaceGrupoCampos);
        } else {
            campoAux.setInterfaceGrupoCampos(null);
        }
        
        if(updateCampoDTO.listaValoresId() != null) {
            ListaValores listaValores = listaValoresRepository.findById(updateCampoDTO.listaValoresId()).get();
            campoAux.setListaValores(listaValores);
        } else {
            campoAux.setListaValores(null);
        }
        
        campoAux = campoRepository.save(campoAux);
        return campoMapper.toDTO(campoAux);
    }

    /**
     * Obtiene la lista completa de campos.
     * @return lista de campos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseCampoDTO> getAllCampos() {
        return campoRepository.findAll().stream()
                .map(campoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un campo por identificador.
     * @param id identificador del campo.
     * @return campo encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseCampoDTO getCampoById(Long id) throws Exception {
        Optional<Campo> campo = campoRepository.findById(id);
        if(campo.isEmpty()) {
            throw new NoExisteException("No existe el campo");
        }
        return campoMapper.toDTO(campo.get());
    }

    /**
     * Obtiene los campos asociados a una interfaz.
     * @param interfazId identificador de la interfaz.
     * @return lista de campos de la interfaz.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseCampoDTO> getCamposByInterfaz(Long interfazId) throws Exception {
        Interfaz interfaz = interfazRepository.findById(interfazId).orElseThrow(() ->
                new NoExisteException("No existe la interfaz")
        );
        return campoRepository.findByInterfaz(interfaz);
    }

}

package uq.com.jdq.coresuite.operacion.lista_valores;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.config.exceptions.RegistroRepetidoException;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListaValoresServiceImpl implements ListaValoresService {

    private final ListaValoresRepository listaValoresRepository;
    private final ListaValoresMapper listaValoresMapper;
    private final EmpresaRepository empresaRepository;

    @Override
    @Transactional
    public ResponseListaValoresDTO createListaValores(CreateListaValoresDTO createListaValoresDTO) throws Exception {
        Empresa empresa = empresaRepository.findById(createListaValoresDTO.empresaId())
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        
        // Validar que no exista una lista de valores con el mismo nombre en la misma empresa
        Optional<ListaValores> listaValoresExistente = listaValoresRepository.findByEmpresaAndNombre(empresa, createListaValoresDTO.nombre());
        if(listaValoresExistente.isPresent()) {
            throw new RegistroRepetidoException("Ya existe una lista de valores con el nombre " + createListaValoresDTO.nombre() + " en la empresa");
        }
        
        ListaValores listaValores = listaValoresMapper.toEntity(createListaValoresDTO);
        listaValores.setEmpresa(empresa);
        listaValores = listaValoresRepository.save(listaValores);
        return listaValoresMapper.toDTO(listaValores);
    }

    @Override
    @Transactional
    public ResponseListaValoresDTO updateListaValores(Long id, UpdateListaValoresDTO updateListaValoresDTO) throws Exception {
        Empresa empresa = empresaRepository.findById(updateListaValoresDTO.empresaId())
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        ListaValores listaValores = listaValoresRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe la lista de valores"));
        
        // Validar que no exista otra lista de valores con el mismo nombre en la misma empresa (excluyendo el actual)
        Optional<ListaValores> listaValoresExistente = listaValoresRepository.findByEmpresaAndNombreAndIdNot(empresa, updateListaValoresDTO.nombre(), id);
        if(listaValoresExistente.isPresent()) {
            throw new RegistroRepetidoException("Ya existe una lista de valores con el nombre " + updateListaValoresDTO.nombre() + " en la empresa");
        }
        
        listaValoresMapper.updateEntityFromDTO(updateListaValoresDTO, listaValores);
        listaValores.setEmpresa(empresa);
        listaValores = listaValoresRepository.save(listaValores);
        return listaValoresMapper.toDTO(listaValores);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseListaValoresDTO> getAllListaValores() throws Exception {
        return listaValoresRepository.findAll().stream().map(listaValoresMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseListaValoresDTO getListaValoresById(Long id) throws Exception {
        return listaValoresRepository.findById(id)
                .map(listaValoresMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe la lista de valores"));
    }

    @Override
    public List<ResponseListaValoresDTO> getListaValoresByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        return listaValoresRepository.findByEmpresa(empresa);
    }

}

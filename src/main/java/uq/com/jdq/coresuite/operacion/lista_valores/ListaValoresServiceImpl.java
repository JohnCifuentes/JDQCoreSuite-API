package uq.com.jdq.coresuite.operacion.lista_valores;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.util.List;

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

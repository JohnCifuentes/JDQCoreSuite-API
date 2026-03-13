package uq.com.jdq.coresuite.operacion.modulo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.config.exceptions.RegistroRepetidoException;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de administracion y consulta de modulos.
 */
@Service
@RequiredArgsConstructor
public class ModuloServiceImpl implements ModuloService {

    private final ModuloRepository moduloRepository;
    private final ModuloMapper moduloMapper;
    private final EmpresaRepository empresaRepository;

    /**
     * Crea un nuevo modulo.
     * @param createModuloDTO datos de creacion.
     * @return modulo creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @Override
    @Transactional
    public ResponseModuloDTO createModulo(CreateModuloDTO createModuloDTO) throws Exception {
        Empresa empresa = empresaRepository.findById(createModuloDTO.empresaId())
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        
        // Validar que no exista un mÃ³dulo con el mismo nombre en la misma empresa
        Optional<Modulo> moduloExistenteNombre = moduloRepository.findByEmpresaAndNombre(empresa, createModuloDTO.nombre());
        if(moduloExistenteNombre.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un mÃ³dulo con el nombre " + createModuloDTO.nombre() + " en la empresa");
        }
        
        // Validar que no exista un mÃ³dulo con el mismo Ã­ndice en la misma empresa
        Optional<Modulo> moduloExistenteIndice = moduloRepository.findByEmpresaAndIndice(empresa, createModuloDTO.indice());
        if(moduloExistenteIndice.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un mÃ³dulo con el Ã­ndice " + createModuloDTO.indice() + " en la empresa");
        }
        
        Modulo modulo = moduloMapper.toEntity(createModuloDTO);
        modulo.setEmpresa(empresa);
        modulo = moduloRepository.save(modulo);
        return moduloMapper.toDTO(modulo);
    }

    /**
     * Actualiza un modulo existente.
     * @param id identificador del modulo.
     * @param updateModuloDTO datos actualizados.
     * @return modulo actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @Override
    @Transactional
    public ResponseModuloDTO updateModulo(Long id, UpdateModuloDTO updateModuloDTO) throws Exception {
        Empresa empresa = empresaRepository.findById(updateModuloDTO.empresaId())
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        Modulo modulo = moduloRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el modulo"));
        
        // Validar que no exista otro mÃ³dulo con el mismo nombre en la misma empresa (excluyendo el actual)
        Optional<Modulo> moduloExistenteNombre = moduloRepository.findByEmpresaAndNombreAndIdNot(empresa, updateModuloDTO.nombre(), id);
        if(moduloExistenteNombre.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un mÃ³dulo con el nombre " + updateModuloDTO.nombre() + " en la empresa");
        }
        
        // Validar que no exista otro mÃ³dulo con el mismo Ã­ndice en la misma empresa (excluyendo el actual)
        Optional<Modulo> moduloExistenteIndice = moduloRepository.findByEmpresaAndIndiceAndIdNot(empresa, updateModuloDTO.indice(), id);
        if(moduloExistenteIndice.isPresent()) {
            throw new RegistroRepetidoException("Ya existe un mÃ³dulo con el Ã­ndice " + updateModuloDTO.indice() + " en la empresa");
        }
        
        moduloMapper.updateEntityFromDTO(updateModuloDTO, modulo);
        modulo.setEmpresa(empresa);
        modulo = moduloRepository.save(modulo);
        return moduloMapper.toDTO(modulo);
    }

    /**
     * Obtiene la lista completa de modulos.
     * @return lista de modulos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseModuloDTO> getAllModulos() throws Exception {
        return moduloRepository.findAll().stream().map(moduloMapper::toDTO).toList();
    }

    /**
     * Obtiene un modulo por identificador.
     * @param id identificador del modulo.
     * @return modulo encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseModuloDTO getModuloById(Long id) throws Exception {
        return moduloRepository.findById(id)
                .map(moduloMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe el modulo"));
    }

    /**
     * Obtiene los modulos asociados a una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de modulos relacionados.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public List<ResponseModuloDTO> getModulosByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        return moduloRepository.findByEmpresa(empresa);
    }

}

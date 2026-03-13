package uq.com.jdq.coresuite.sistema.sesion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.seguridad.usuario.UsuarioRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio encargado de administrar sesiones.
 */
@Service
@RequiredArgsConstructor
public class SesionServiceImpl implements SesionService {

    private final SesionRepository sesionRepository;
    private final SesionMapper sesionMapper;
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    /**
     * Crea una sesion asociada a una empresa y un usuario.
     * @param createSesionDTO datos de la sesion.
     * @return sesion creada.
     * @throws Exception si la empresa o el usuario no existen.
     */
    @Override
    @Transactional
    public ResponseSesionDTO createSesion(CreateSesionDTO createSesionDTO) throws Exception {
        Sesion sesion = sesionMapper.toEntity(createSesionDTO);
        Long empresaId = createSesionDTO.empresaId();
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        Long usuarioId = createSesionDTO.usuarioId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoExisteException("No existe el usuario")
        );
        sesion.setEmpresa(empresa);
        sesion.setUsuario(usuario);
        sesion = sesionRepository.save(sesion);
        return sesionMapper.toDTO(sesion);
    }

    /**
     * Inactiva la sesion activa mas reciente de un usuario.
     * @param usuario usuario propietario de la sesion.
     * @throws Exception si no existe una sesion activa para el usuario.
     */
    @Override
    @Transactional
    public void inactiveSesion(Usuario usuario) throws Exception {
        Sesion sesion = sesionRepository
                .findTopByUsuarioAndEstadoOrderByFechaInicioDesc(usuario, "A")
                .orElseThrow(() -> new NoExisteException("No existe una sesiÃ³n activa para el usuario"));
        sesion.setFechaCierre(LocalDateTime.now());
        sesion.setFechaUltimoAcceso(LocalDateTime.now());
        sesion.setEstado("I");
        sesionRepository.save(sesion);
    }

    /**
     * Obtiene todas las sesiones registradas.
     * @return lista de sesiones.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseSesionDTO> getAllSesiones() throws Exception {
        return sesionRepository.findAll().stream()
                .map(sesionMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Consulta una sesion por identificador.
     * @param id identificador de la sesion.
     * @return sesion encontrada.
     * @throws Exception si la sesion no existe.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseSesionDTO getSesionById(Long id) throws Exception {
        return sesionRepository.findById(id)
                .map(sesionMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe la sesiÃ³n"));
    }

    /**
     * Obtiene las sesiones asociadas a una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de sesiones.
     * @throws Exception si la empresa no existe.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseSesionDTO> getSesionesByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(
                () -> new NoExisteException("No existe la empresa")
        );
        return sesionRepository.findByEmpresa(empresa);
    }

}

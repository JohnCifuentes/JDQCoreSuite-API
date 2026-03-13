package uq.com.jdq.coresuite.sistema.empresa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.catalogo.departamento.Departamento;
import uq.com.jdq.coresuite.catalogo.departamento.DepartamentoServiceImp;
import uq.com.jdq.coresuite.catalogo.municipio.Municipio;
import uq.com.jdq.coresuite.catalogo.municipio.MunicipioServiceImp;
import uq.com.jdq.coresuite.catalogo.pais.Pais;
import uq.com.jdq.coresuite.catalogo.pais.PaisServiceImp;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacion;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacionServiceImp;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.config.exceptions.RegistroRepetidoException;
import uq.com.jdq.coresuite.notificacion.EmailDTO;
import uq.com.jdq.coresuite.notificacion.NotificacionService;
import uq.com.jdq.coresuite.seguridad.rol.CreateRolDTO;
import uq.com.jdq.coresuite.seguridad.rol.ResponseRolDTO;
import uq.com.jdq.coresuite.seguridad.rol.RolServiceImpl;
import uq.com.jdq.coresuite.seguridad.rolusuario.CreateRolUsuarioDTO;
import uq.com.jdq.coresuite.seguridad.rolusuario.RolUsuarioServiceImpl;
import uq.com.jdq.coresuite.seguridad.usuario.CreateUsuarioDTO;
import uq.com.jdq.coresuite.seguridad.usuario.ResponseUsuarioDTO;
import uq.com.jdq.coresuite.seguridad.usuario.UsuarioServiceImpl;
import uq.com.jdq.coresuite.sistema.licencia.CreateLicenciaDTO;
import uq.com.jdq.coresuite.sistema.licencia.LicenciaServiceImpl;
import uq.com.jdq.coresuite.sistema.sesion.SesionServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio encargado de administrar empresas.
 */
@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;
    private final TipoIdentificacionServiceImp tipoIdentificacionServiceImp;
    private final PaisServiceImp paisServiceImp;
    private final DepartamentoServiceImp departamentoServicioImp;
    private final MunicipioServiceImp municipioServiceImp;
    private final LicenciaServiceImpl licenciaService;
    private final RolServiceImpl rolService;
    private final UsuarioServiceImpl usuarioService;
    private final RolUsuarioServiceImpl rolUsuarioService;
    private final NotificacionService notificacionService;

    /**
     * Crea una empresa, su configuracion inicial y los recursos base de seguridad.
     * @param createEmpresaDTO datos de la empresa.
     * @return empresa creada.
     * @throws Exception si ocurre un error de negocio durante el registro.
     */
    @Override
    @Transactional
    public ResponseEmpresaDTO createEmpresa(CreateEmpresaDTO createEmpresaDTO) throws Exception {
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionServiceImp.getTipoIdentificacion(createEmpresaDTO.tipoIdentificacionId());
        Pais pais = paisServiceImp.getPais(createEmpresaDTO.paisId());
        Departamento departamento = departamentoServicioImp.getDepartamento(createEmpresaDTO.departamentoId());
        Municipio municipio = municipioServiceImp.getMunicipio(createEmpresaDTO.municipioId());
        /**
         * Validaciones
         */
        if (empresaRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipoIdentificacion, createEmpresaDTO.numeroIdentificacion()).isPresent()) {
            throw new RegistroRepetidoException("Ya existe una empresa registrada con este tipo y nÃºmero de identificaciÃ³n");
        }
        /**/
        if (empresaRepository.findByCorreoElectronico(createEmpresaDTO.correoElectronico()).isPresent()) {
            throw new RegistroRepetidoException("Ya existe una empresa registrada con este correo electronico");
        }
        /**
         *
         */
        Empresa empresa = empresaMapper.toEntity(createEmpresaDTO);
        empresa.setTipoIdentificacion(tipoIdentificacion);
        empresa.setPais(pais);
        empresa.setDepartamento(departamento);
        empresa.setMunicipio(municipio);
        empresa = empresaRepository.save(empresa);
        /**
         * Crear ROL/ADMIN
         */
        CreateRolDTO rolDTO = new CreateRolDTO(empresa.getId(), "ADMIN", "ROLE_ADMIN");
        ResponseRolDTO rol = rolService.createRol(rolDTO);
        /**
         * Crear Usuario/ADMIN
         */
        CreateUsuarioDTO usuarioDTO = new CreateUsuarioDTO(empresa.getId(), empresa.getTipoIdentificacion().getId(), empresa.getNumeroIdentificacion(),
                empresa.getRazonSocial(), null, "ADMIN", null, empresa.getTelefono(), empresa.getCorreoElectronico());
        ResponseUsuarioDTO usuario = usuarioService.createUsuario(usuarioDTO);
        /**
         * Asignar RolUsuario/ADMIN
         */
        CreateRolUsuarioDTO rolUsuarioDTO = new CreateRolUsuarioDTO(empresa.getId(), rol.id(), usuario.id());
        rolUsuarioService.createRolUsuario(rolUsuarioDTO);
        /**
         *
         */
        CreateLicenciaDTO licenciaDTO = new CreateLicenciaDTO(empresa.getId(), 2L, LocalDate.now(), LocalDate.now().plusMonths(3), true);
        licenciaService.createLicencia(licenciaDTO);
        /**
         * NotificaciÃ³n vÃ­a email
         */
        String cuerpo = """
        Hola %s,
        
        Â¡Bienvenido a JDQ - CoreSuite!
        
        Su empresa ha sido registrada exitosamente en nuestra plataforma.
        
        Puede acceder al sistema desde el siguiente enlace:
        https://jdq-coresuite-app.web.app/
 
        Atentamente,
        Equipo JDQ - CoreSuite
        """.formatted(
                empresa.getRazonSocial()
        );
        EmailDTO emailDTO = new EmailDTO("Bienvenido a JDQ - CoreSuite", cuerpo, usuario.correoElectronico());
        notificacionService.enviarNotificacion(emailDTO);
        return empresaMapper.toDTO(empresa);
    }

    /**
     * Actualiza una empresa existente validando unicidad de identificacion y correo.
     * @param id identificador de la empresa.
     * @param updateEmpresaDTO nuevos datos de la empresa.
     * @return empresa actualizada.
     * @throws Exception si ocurre un error de negocio.
     */
    @Override
    @Transactional
    public ResponseEmpresaDTO updateEmpresa(Long id, UpdateEmpresaDTO updateEmpresaDTO) throws Exception {
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionServiceImp.getTipoIdentificacion(updateEmpresaDTO.tipoIdentificacionId());
        Pais pais = paisServiceImp.getPais(updateEmpresaDTO.paisId());
        Departamento departamento = departamentoServicioImp.getDepartamento(updateEmpresaDTO.departamentoId());
        Municipio municipio = municipioServiceImp.getMunicipio(updateEmpresaDTO.municipioId());
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        /**
         * Validaciones
         */
        if (empresaRepository.findByTipoIdentificacionAndNumeroIdentificacionAndIdNot(tipoIdentificacion, updateEmpresaDTO.numeroIdentificacion(), empresa.getId()).isPresent()) {
            throw new RegistroRepetidoException("Ya existe una empresa registrada con este tipo y nÃºmero de identificaciÃ³n");
        }
        /**/
        if (empresaRepository.findByCorreoElectronicoAndIdNot(updateEmpresaDTO.correoElectronico(), empresa.getId()).isPresent()) {
            throw new RegistroRepetidoException("Ya existe una empresa registrar con este correo electronico");
        }
        empresaMapper.updateEntityFromDTO(updateEmpresaDTO, empresa);
        empresa.setTipoIdentificacion(tipoIdentificacion);
        empresa.setPais(pais);
        empresa.setDepartamento(departamento);
        empresa.setMunicipio(municipio);
        empresa = empresaRepository.save(empresa);
        return empresaMapper.toDTO(empresa);
    }

    /**
     * Cambia el estado de una empresa.
     * @param id identificador de la empresa.
     * @param inactiveEmpresaDTO datos del nuevo estado.
     * @return empresa actualizada.
     * @throws Exception si la empresa no existe.
     */
    @Override
    @Transactional
    public ResponseEmpresaDTO inactiveEmpresa(Long id, InactiveEmpresaDTO inactiveEmpresaDTO) throws Exception {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
        empresaMapper.inactiveEntityFromDTO(inactiveEmpresaDTO, empresa);
        empresa = empresaRepository.save(empresa);
        return empresaMapper.toDTO(empresa);
    }

    /**
     * Obtiene todas las empresas registradas.
     * @return lista de empresas.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseEmpresaDTO> getAllEmpresas() throws Exception {
        return empresaRepository.findAll().stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Consulta una empresa por identificador.
     * @param id identificador de la empresa.
     * @return empresa encontrada.
     * @throws Exception si la empresa no existe.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEmpresaDTO getEmpresaById(Long id) throws Exception {
        return empresaRepository.findById(id)
                .map(empresaMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe la empresa"));
    }

}

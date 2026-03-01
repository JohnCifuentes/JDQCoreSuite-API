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
import uq.com.jdq.coresuite.seguridad.rol.CreateRolDTO;
import uq.com.jdq.coresuite.seguridad.rol.ResponseRolDTO;
import uq.com.jdq.coresuite.seguridad.rol.RolServiceImpl;
import uq.com.jdq.coresuite.seguridad.rolusuario.CreateRolUsuarioDTO;
import uq.com.jdq.coresuite.seguridad.rolusuario.RolUsuarioServiceImpl;
import uq.com.jdq.coresuite.seguridad.usuario.CreateUsuarioDTO;
import uq.com.jdq.coresuite.seguridad.usuario.ResponseUsuarioDTO;
import uq.com.jdq.coresuite.seguridad.usuario.UsuarioServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;
    private final TipoIdentificacionServiceImp tipoIdentificacionServiceImp;
    private final PaisServiceImp paisServiceImp;
    private final DepartamentoServiceImp departamentoServicioImp;
    private final MunicipioServiceImp municipioServiceImp;
    private final RolServiceImpl rolService;
    private final UsuarioServiceImpl usuarioService;
    private final RolUsuarioServiceImpl rolUsuarioService;


    @Override
    @Transactional
    public ResponseEmpresaDTO createEmpresa(CreateEmpresaDTO createEmpresaDTO) throws Exception {
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionServiceImp.getTipoIdentificacion(createEmpresaDTO.tipoIdentificacionId());
        Pais pais = paisServiceImp.getPais(createEmpresaDTO.paisId());
        Departamento departamento = departamentoServicioImp.getDepartamento(createEmpresaDTO.departamentoId());
        Municipio municipio = municipioServiceImp.getMunicipio(createEmpresaDTO.municipioId());
        /**
         *
         */
        Empresa empresa = empresaMapper.toEntity(createEmpresaDTO);
        empresa.setTipoIdentificacion(tipoIdentificacion);
        empresa.setPais(pais);
        empresa.setDepartamento(departamento);
        empresa.setMunicipio(municipio);

        System.out.println("tipo identificacion :" + tipoIdentificacion.getId());
        System.out.println("pais :" + pais.getId());
        System.out.println("departamento :" + departamento.getId());
        System.out.println("municipio :" + municipio.getId());
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
        return empresaMapper.toDTO(empresa);
    }

    @Override
    @Transactional
    public ResponseEmpresaDTO updateEmpresa(Long id, UpdateEmpresaDTO updateEmpresaDTO) throws Exception {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionServiceImp.getTipoIdentificacion(updateEmpresaDTO.tipoIdentificacionId());
        Pais pais = paisServiceImp.getPais(updateEmpresaDTO.paisId());
        Departamento departamento = departamentoServicioImp.getDepartamento(updateEmpresaDTO.departamentoId());
        Municipio municipio = municipioServiceImp.getMunicipio(updateEmpresaDTO.municipioId());
        empresaMapper.updateEntityFromDTO(updateEmpresaDTO, empresa);
        empresa.setTipoIdentificacion(tipoIdentificacion);
        empresa.setPais(pais);
        empresa.setDepartamento(departamento);
        empresa.setMunicipio(municipio);
        empresa = empresaRepository.save(empresa);
        return empresaMapper.toDTO(empresa);
    }

    @Override
    @Transactional
    public ResponseEmpresaDTO inactiveEmpresa(Long id, InactiveEmpresaDTO inactiveEmpresaDTO) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        empresaMapper.inactiveEntityFromDTO(inactiveEmpresaDTO, empresa);
        empresa = empresaRepository.save(empresa);
        return empresaMapper.toDTO(empresa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseEmpresaDTO> getAllEmpresas() {
        return empresaRepository.findAll().stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEmpresaDTO getEmpresaById(Long id) {
        return empresaRepository.findById(id)
                .map(empresaMapper::toDTO)
                .orElse(null);
    }

}

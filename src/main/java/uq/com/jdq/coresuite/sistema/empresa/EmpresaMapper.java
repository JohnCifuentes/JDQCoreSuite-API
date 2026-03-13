package uq.com.jdq.coresuite.sistema.empresa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper para convertir entre entidades de empresa y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface EmpresaMapper {

    /**
     * Convierte el DTO de creacion en una entidad de empresa.
     * @param createEmpresaDTO datos de la empresa.
     * @return entidad lista para persistir.
     */
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    Empresa toEntity(CreateEmpresaDTO createEmpresaDTO);

    /**
     * Actualiza una entidad de empresa con datos del DTO de edicion.
     * @param updateEmpresaDTO datos actualizados de la empresa.
     * @param empresa entidad que sera modificada.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateEmpresaDTO updateEmpresaDTO, @MappingTarget Empresa empresa);

    /**
     * Aplica el cambio de estado sobre una empresa.
     * @param inactiveEmpresaDTO nuevo estado.
     * @param empresa entidad que sera inactivada.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void inactiveEntityFromDTO(InactiveEmpresaDTO inactiveEmpresaDTO, @MappingTarget Empresa empresa);

    /**
     * Convierte una entidad de empresa en su DTO de respuesta.
     * @param empresa entidad consultada.
     * @return DTO con la informacion de la empresa.
     */
    ResponseEmpresaDTO toDTO(Empresa empresa);

}

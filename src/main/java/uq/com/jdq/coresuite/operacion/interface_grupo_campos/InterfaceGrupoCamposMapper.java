package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapeador entre la entidad InterfaceGrupoCampos y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface InterfaceGrupoCamposMapper {
    /**
     * Convierte un DTO de creacion en entidad de grupo de campos.
     * @param createInterfaceGrupoCamposDTO datos de creacion.
     * @return entidad de grupo de campos.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "interfaz", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "usuarioActualizacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    InterfaceGrupoCampos toEntity(CreateInterfaceGrupoCamposDTO createInterfaceGrupoCamposDTO);

    /**
     * Actualiza una entidad de grupo de campos a partir de un DTO.
     * @param updateInterfaceGrupoCamposDTO datos actualizados.
     * @param interfaceGrupoCampos entidad a modificar.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "interfaz", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateInterfaceGrupoCamposDTO updateInterfaceGrupoCamposDTO, @MappingTarget InterfaceGrupoCampos interfaceGrupoCampos);

    /**
     * Convierte una entidad de grupo de campos en DTO de respuesta.
     * @param interfaceGrupoCampos entidad del grupo de campos.
     * @return DTO de respuesta.
     */
    ResponseInterfaceGrupoCamposDTO toDTO(InterfaceGrupoCampos interfaceGrupoCampos);
}

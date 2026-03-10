package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InterfaceGrupoCamposMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "interfaz", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "usuarioActualizacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    InterfaceGrupoCampos toEntity(CreateInterfaceGrupoCamposDTO createInterfaceGrupoCamposDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "interfaz", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateInterfaceGrupoCamposDTO updateInterfaceGrupoCamposDTO, @MappingTarget InterfaceGrupoCampos interfaceGrupoCampos);

    ResponseInterfaceGrupoCamposDTO toDTO(InterfaceGrupoCampos interfaceGrupoCampos);
}

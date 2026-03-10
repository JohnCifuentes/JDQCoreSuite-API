package uq.com.jdq.coresuite.operacion.interfaz;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InterfazMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modulo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "usuarioActualizacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Interfaz toEntity(CreateInterfazDTO createInterfazDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modulo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateInterfazDTO updateInterfazDTO, @MappingTarget Interfaz interfaz);

    ResponseInterfazDTO toDTO(Interfaz interfaz);
}

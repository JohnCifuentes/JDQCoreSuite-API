package uq.com.jdq.coresuite.operacion.modulo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ModuloMapper {

    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    Modulo toEntity(CreateModuloDTO createModuloDTO);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateModuloDTO updateModuloDTO, @MappingTarget Modulo modulo);

    ResponseModuloDTO toDTO(Modulo modulo);

}

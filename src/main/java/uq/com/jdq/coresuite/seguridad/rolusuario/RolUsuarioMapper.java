package uq.com.jdq.coresuite.seguridad.rolusuario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RolUsuarioMapper {

    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    RolUsuario toEntity(CreateRolUsuarioDTO createRolUsuarioDTO);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateRolUsuarioDTO updateRolUsuarioDTO, @MappingTarget RolUsuario rolUsuario);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void inactiveEntityFromDTO(InactiveRolUsuarioDTO inactiveRolUsuarioDTO, @MappingTarget RolUsuario rolUsuario);

    ResponseRolUsuarioDTO toDTO(RolUsuario rolUsuario);

}

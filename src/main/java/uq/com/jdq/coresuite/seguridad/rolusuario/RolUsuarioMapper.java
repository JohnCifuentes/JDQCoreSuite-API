package uq.com.jdq.coresuite.seguridad.rolusuario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper para convertir entre entidades de asignacion rol-usuario y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface RolUsuarioMapper {

    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    /**
     * Convierte el DTO de creacion en una entidad rol-usuario.
     * @param createRolUsuarioDTO datos de la asignacion.
     * @return entidad lista para persistir.
     */
    RolUsuario toEntity(CreateRolUsuarioDTO createRolUsuarioDTO);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    /**
     * Actualiza una entidad rol-usuario con la informacion del DTO de edicion.
     * @param updateRolUsuarioDTO datos actualizados de la asignacion.
     * @param rolUsuario entidad que sera modificada.
     */
    void updateEntityFromDTO(UpdateRolUsuarioDTO updateRolUsuarioDTO, @MappingTarget RolUsuario rolUsuario);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    /**
     * Aplica el cambio de estado sobre una entidad rol-usuario.
     * @param inactiveRolUsuarioDTO informacion del nuevo estado.
     * @param rolUsuario entidad que sera inactivada.
     */
    void inactiveEntityFromDTO(InactiveRolUsuarioDTO inactiveRolUsuarioDTO, @MappingTarget RolUsuario rolUsuario);

    /**
     * Convierte una entidad rol-usuario en su DTO de respuesta.
     * @param rolUsuario entidad consultada.
     * @return DTO con la informacion de la asignacion.
     */
    ResponseRolUsuarioDTO toDTO(RolUsuario rolUsuario);

}

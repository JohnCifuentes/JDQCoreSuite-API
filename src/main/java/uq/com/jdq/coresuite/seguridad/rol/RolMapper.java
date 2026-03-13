package uq.com.jdq.coresuite.seguridad.rol;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper para convertir entre entidades de rol y sus DTO asociados.
 */
@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    /**
     * Convierte el DTO de creacion en una entidad de rol.
     * @param createRolDTO datos de entrada del rol.
     * @return entidad lista para persistir.
     */
    Rol toEntity(CreateRolDTO createRolDTO);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    /**
     * Actualiza una entidad de rol con la informacion del DTO de edicion.
     * @param updateRolDTO datos nuevos del rol.
     * @param rol entidad que sera modificada.
     */
    void updateEntityFromDTO(UpdateRolDTO updateRolDTO, @MappingTarget Rol rol);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    /**
     * Aplica el cambio de estado sobre una entidad de rol.
     * @param inactiveRolDTO informacion del nuevo estado.
     * @param rol entidad que sera inactivada.
     */
    void inactiveEntityFromDTO(InactiveRolDTO inactiveRolDTO, @MappingTarget Rol rol);

    /**
     * Convierte una entidad de rol en su DTO de respuesta.
     * @param rol entidad consultada.
     * @return DTO con la informacion expuesta del rol.
     */
    ResponseRolDTO toDTO(Rol rol);

}

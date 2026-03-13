package uq.com.jdq.coresuite.seguridad.usuario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper para convertir entre entidades de usuario y sus DTO asociados.
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    /**
     * Convierte el DTO de creacion en una entidad de usuario.
     * @param createUsuarioDTO datos del usuario a registrar.
     * @return entidad lista para persistir.
     */
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    Usuario toEntity(CreateUsuarioDTO createUsuarioDTO);

    /**
     * Actualiza una entidad de usuario con la informacion del DTO de edicion.
     * @param updateUsuarioDTO datos nuevos del usuario.
     * @param usuario entidad que sera modificada.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateUsuarioDTO updateUsuarioDTO, @MappingTarget Usuario usuario);

    /**
     * Aplica el cambio de estado sobre una entidad de usuario.
     * @param inactiveUsuarioDTO informacion del nuevo estado.
     * @param usuario entidad que sera inactivada.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void inactiveEntityFromDTO(InactiveUsuarioDTO inactiveUsuarioDTO, @MappingTarget Usuario usuario);

    /**
     * Actualiza los datos relacionados con la contrasena del usuario.
     * @param updateUsuarioDTO datos utilizados para el cambio.
     * @param usuario entidad que sera modificada.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void cambiarPassword(UpdateUsuarioDTO updateUsuarioDTO, @MappingTarget Usuario usuario);

    /**
     * Convierte una entidad de usuario en su DTO de respuesta.
     * @param usuario entidad consultada.
     * @return DTO con la informacion expuesta del usuario.
     */
    ResponseUsuarioDTO toDTO(Usuario usuario);

}

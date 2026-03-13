package uq.com.jdq.coresuite.sistema.sesion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper para convertir entre entidades de sesion y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface SesionMapper {

    /**
     * Convierte el DTO de creacion en una entidad de sesion.
     * @param createSesionDTO datos de la sesion.
     * @return entidad lista para persistir.
     */
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    Sesion toEntity(CreateSesionDTO createSesionDTO);

    /**
     * Actualiza una entidad de sesion con datos del DTO de edicion.
     * @param updateSesionDTO datos actualizados de la sesion.
     * @param sesion entidad que sera modificada.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateSesionDTO updateSesionDTO, @MappingTarget Sesion sesion);

    /**
     * Convierte una entidad de sesion en su DTO de respuesta.
     * @param sesion entidad consultada.
     * @return DTO con la informacion de la sesion.
     */
    ResponseSesionDTO toDTO(Sesion sesion);

}

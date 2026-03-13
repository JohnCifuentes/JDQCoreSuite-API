package uq.com.jdq.coresuite.operacion.modulo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapeador entre la entidad Modulo y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface ModuloMapper {

    /**
     * Convierte un DTO de creacion en entidad modulo.
     * @param createModuloDTO datos de creacion.
     * @return entidad modulo.
     */
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    Modulo toEntity(CreateModuloDTO createModuloDTO);

    /**
     * Actualiza una entidad modulo a partir de un DTO.
     * @param updateModuloDTO datos actualizados.
     * @param modulo entidad a modificar.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateModuloDTO updateModuloDTO, @MappingTarget Modulo modulo);

    /**
     * Convierte una entidad modulo en DTO de respuesta.
     * @param modulo entidad modulo.
     * @return DTO de respuesta.
     */
    ResponseModuloDTO toDTO(Modulo modulo);

}

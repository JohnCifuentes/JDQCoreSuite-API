package uq.com.jdq.coresuite.operacion.tipo_validacion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapeador entre la entidad TipoValidacion y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoValidacionMapper {
    /**
     * Convierte un DTO de creacion en entidad tipo de validacion.
     * @param createTipoValidacionDTO datos de creacion.
     * @return entidad tipo de validacion.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "usuarioActualizacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    TipoValidacion toEntity(CreateTipoValidacionDTO createTipoValidacionDTO);

    /**
     * Actualiza una entidad tipo de validacion a partir de un DTO de actualizacion.
     * @param updateTipoValidacionDTO datos actualizados.
     * @param tipoValidacion entidad a modificar.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateTipoValidacionDTO updateTipoValidacionDTO, @MappingTarget TipoValidacion tipoValidacion);

    /**
     * Convierte una entidad tipo de validacion en DTO de respuesta.
     * @param tipoValidacion entidad tipo de validacion.
     * @return DTO de respuesta.
     */
    ResponseTipoValidacionDTO toDTO(TipoValidacion tipoValidacion);
}

package uq.com.jdq.coresuite.operacion.campo_validacion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapeador entre la entidad CampoValidacion y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface CampoValidacionMapper {
    /**
     * Convierte un DTO de creacion en entidad de validacion de campo.
     * @param createCampoValidacionDTO datos de creacion.
     * @return entidad de validacion de campo.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "campo", ignore = true)
    @Mapping(target = "tipoValidacion", ignore = true)
    @Mapping(target = "campoReferencia", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "usuarioActualizacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    CampoValidacion toEntity(CreateCampoValidacionDTO createCampoValidacionDTO);

    /**
     * Actualiza una entidad de validacion de campo a partir de un DTO.
     * @param updateCampoValidacionDTO datos actualizados.
     * @param campoValidacion entidad a modificar.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "campo", ignore = true)
    @Mapping(target = "tipoValidacion", ignore = true)
    @Mapping(target = "campoReferencia", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateCampoValidacionDTO updateCampoValidacionDTO, @MappingTarget CampoValidacion campoValidacion);

    /**
     * Convierte una entidad de validacion de campo en DTO de respuesta.
     * @param campoValidacion entidad de validacion.
     * @return DTO de respuesta.
     */
    ResponseCampoValidacionDTO toDTO(CampoValidacion campoValidacion);
}

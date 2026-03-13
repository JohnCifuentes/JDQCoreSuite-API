package uq.com.jdq.coresuite.operacion.campo_dependencia;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapeador entre la entidad CampoDependencia y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface CampoDependenciaMapper {
    /**
     * Convierte un DTO de creacion en entidad de dependencia.
     * @param createCampoDependenciaDTO datos de creacion.
     * @return entidad de dependencia.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "campo", ignore = true)
    @Mapping(target = "campoDependiente", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "usuarioActualizacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    CampoDependencia toEntity(CreateCampoDependenciaDTO createCampoDependenciaDTO);

    /**
     * Actualiza una entidad dependencia a partir de un DTO de actualizacion.
     * @param updateCampoDependenciaDTO datos actualizados.
     * @param campoDependencia entidad a modificar.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "campo", ignore = true)
    @Mapping(target = "campoDependiente", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateCampoDependenciaDTO updateCampoDependenciaDTO, @MappingTarget CampoDependencia campoDependencia);

    /**
     * Convierte una entidad dependencia en DTO de respuesta.
     * @param campoDependencia entidad dependencia.
     * @return DTO de respuesta.
     */
    ResponseCampoDependenciaDTO toDTO(CampoDependencia campoDependencia);
}

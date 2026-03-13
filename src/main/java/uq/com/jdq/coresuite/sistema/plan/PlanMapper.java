package uq.com.jdq.coresuite.sistema.plan;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper para convertir entre entidades de plan y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface PlanMapper {

    /**
     * Convierte el DTO de creacion en una entidad de plan.
     * @param createPlanDTO datos del plan.
     * @return entidad lista para persistir.
     */
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    Plan toEntity(CreatePlanDTO createPlanDTO);

    /**
     * Actualiza una entidad de plan con datos del DTO de edicion.
     * @param updatePlanDTO nuevos datos del plan.
     * @param plan entidad que sera modificada.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdatePlanDTO updatePlanDTO, @MappingTarget Plan plan);

    /**
     * Aplica el cambio de estado sobre un plan.
     * @param inactivePlanDTO nuevo estado.
     * @param plan entidad que sera inactivada.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void inactiveEntityFromDTO(InactivePlanDTO inactivePlanDTO, @MappingTarget Plan plan);

    /**
     * Convierte una entidad de plan en su DTO de respuesta.
     * @param plan entidad consultada.
     * @return DTO con la informacion del plan.
     */
    ResponsePlanDTO toDTO(Plan plan);

}

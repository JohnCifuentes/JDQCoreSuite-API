package uq.com.jdq.coresuite.operacion.tipo_campo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapeador entre la entidad TipoCampo y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoCampoMapper {

    /**
     * Convierte un DTO de creacion en entidad tipo de campo.
     * @param createTipoCampoDTO datos de creacion.
     * @return entidad tipo de campo.
     */
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    TipoCampo toEntity(CreateTipoCampoDTO createTipoCampoDTO);

    /**
     * Actualiza una entidad tipo de campo a partir de un DTO de actualizacion.
     * @param updateTipoCampoDTO datos actualizados.
     * @param tipoCampo entidad a modificar.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateTipoCampoDTO updateTipoCampoDTO, @MappingTarget TipoCampo tipoCampo);

    /**
     * Convierte una entidad tipo de campo en DTO de respuesta.
     * @param tipoCampo entidad tipo de campo.
     * @return DTO de respuesta.
     */
    ResponseTipoCampoDTO toDTO(TipoCampo tipoCampo);

}

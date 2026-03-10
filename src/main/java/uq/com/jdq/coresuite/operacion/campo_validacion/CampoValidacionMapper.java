package uq.com.jdq.coresuite.operacion.campo_validacion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CampoValidacionMapper {
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

    ResponseCampoValidacionDTO toDTO(CampoValidacion campoValidacion);
}

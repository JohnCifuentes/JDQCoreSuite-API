package uq.com.jdq.coresuite.operacion.campo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CampoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "interfaz", ignore = true)
    @Mapping(target = "interfaceGrupoCampos", ignore = true)
    @Mapping(target = "tipoCampo", ignore = true)
    @Mapping(target = "listaValores", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "usuarioActualizacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Campo toEntity(CreateCampoDTO createCampoDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "interfaz", ignore = true)
    @Mapping(target = "interfaceGrupoCampos", ignore = true)
    @Mapping(target = "tipoCampo", ignore = true)
    @Mapping(target = "listaValores", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateCampoDTO updateCampoDTO, @MappingTarget Campo campo);

    ResponseCampoDTO toDTO(Campo campo);
}

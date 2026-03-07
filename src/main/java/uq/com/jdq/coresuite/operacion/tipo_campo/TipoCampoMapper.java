package uq.com.jdq.coresuite.operacion.tipo_campo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TipoCampoMapper {

    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    TipoCampo toEntity(CreateTipoCampoDTO createTipoCampoDTO);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateTipoCampoDTO updateTipoCampoDTO, @MappingTarget TipoCampo tipoCampo);

    ResponseTipoCampoDTO toDTO(TipoCampo tipoCampo);

}

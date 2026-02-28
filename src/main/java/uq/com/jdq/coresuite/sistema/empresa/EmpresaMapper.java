package uq.com.jdq.coresuite.sistema.empresa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {

    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    Empresa toEntity(CreateEmpresaDTO createEmpresaDTO);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateEmpresaDTO updateEmpresaDTO, @MappingTarget Empresa empresa);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void inactiveEntityFromDTO(InactiveEmpresaDTO inactiveEmpresaDTO, @MappingTarget Empresa empresa);

    ResponseEmpresaDTO toDTO(Empresa empresa);

}

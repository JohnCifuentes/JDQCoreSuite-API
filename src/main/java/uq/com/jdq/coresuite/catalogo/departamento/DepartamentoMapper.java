package uq.com.jdq.coresuite.catalogo.departamento;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    Departamento toEntity(DepartamentoDTO departamentoDTO);

    DepartamentoDTO toDTO(Departamento departamento);

}

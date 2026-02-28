package uq.com.jdq.coresuite.catalogo.departamento;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    /**
     * OBTENER
     */

    DepartamentoDTO toDTO(Departamento departamento);

}

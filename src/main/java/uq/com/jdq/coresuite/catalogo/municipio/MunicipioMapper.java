package uq.com.jdq.coresuite.catalogo.municipio;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MunicipioMapper {

    MunicipioDTO toDto(Municipio municipio);

}

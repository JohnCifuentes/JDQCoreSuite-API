package uq.com.jdq.coresuite.catalogo.municipio;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MunicipioMapper {

    Municipio toEntity(MunicipioDTO municipioDTO);

    MunicipioDTO toDto(Municipio municipio);

}

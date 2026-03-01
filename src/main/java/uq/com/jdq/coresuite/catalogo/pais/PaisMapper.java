package uq.com.jdq.coresuite.catalogo.pais;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaisMapper {

    Pais toEntity(PaisDTO paisDTO);

    PaisDTO toDto(Pais pais);

}

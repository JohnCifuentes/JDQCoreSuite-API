package uq.com.jdq.coresuite.catalogo.genero;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    GeneroDTO toDTO(Genero genero);

}

package uq.com.jdq.coresuite.catalogo.genero;

import org.mapstruct.Mapper;

/**
 * Mapeador entre la entidad Genero y su DTO.
 */
@Mapper(componentModel = "spring")
public interface GeneroMapper {

    /**
     * Convierte una entidad de genero en DTO.
     * @param genero entidad genero.
     * @return DTO del genero.
     */
    GeneroDTO toDTO(Genero genero);

}

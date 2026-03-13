package uq.com.jdq.coresuite.catalogo.municipio;

import org.mapstruct.Mapper;

/**
 * Mapeador entre la entidad Municipio y su DTO.
 */
@Mapper(componentModel = "spring")
public interface MunicipioMapper {

    /**
     * Convierte un DTO de municipio en entidad.
     * @param municipioDTO datos del municipio.
     * @return entidad municipio.
     */
    Municipio toEntity(MunicipioDTO municipioDTO);

    /**
     * Convierte una entidad de municipio en DTO.
     * @param municipio entidad municipio.
     * @return DTO del municipio.
     */
    MunicipioDTO toDto(Municipio municipio);

}

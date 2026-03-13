package uq.com.jdq.coresuite.catalogo.pais;

import org.mapstruct.Mapper;

/**
 * Mapeador entre la entidad Pais y su DTO.
 */
@Mapper(componentModel = "spring")
public interface PaisMapper {

    /**
     * Convierte un DTO de pais en entidad.
     * @param paisDTO datos del pais.
     * @return entidad pais.
     */
    Pais toEntity(PaisDTO paisDTO);

    /**
     * Convierte una entidad de pais en DTO.
     * @param pais entidad pais.
     * @return DTO del pais.
     */
    PaisDTO toDto(Pais pais);

}

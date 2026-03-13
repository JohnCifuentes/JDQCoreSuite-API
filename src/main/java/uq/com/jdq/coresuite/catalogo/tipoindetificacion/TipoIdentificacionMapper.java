package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

import org.mapstruct.Mapper;

/**
 * Mapeador entre la entidad TipoIdentificacion y su DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoIdentificacionMapper {

    /**
     * Convierte un DTO en entidad tipo de identificacion.
     * @param tipoIdentificacionDTO datos del tipo de identificacion.
     * @return entidad tipo de identificacion.
     */
    TipoIdentificacion toEntity(TipoIdentificacionDTO tipoIdentificacionDTO);

    /**
     * Convierte una entidad tipo de identificacion en DTO.
     * @param tipoIdentificacion entidad tipo de identificacion.
     * @return DTO del tipo de identificacion.
     */
    TipoIdentificacionDTO toDto(TipoIdentificacion tipoIdentificacion);

}

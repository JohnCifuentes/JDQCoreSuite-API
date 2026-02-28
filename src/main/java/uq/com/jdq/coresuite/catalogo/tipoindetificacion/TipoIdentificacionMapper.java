package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoIdentificacionMapper {

    TipoIdentificacionDTO toDto(TipoIdentificacion tipoIdentificacion);

}

package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ListaValoresDetalleMapper {

    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    ListaValoresDetalle toEntity(CreateListaValoresDetalleDTO createListaValoresDetalleDTO);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateListaValoresDetalleDTO updateListaValoresDetalleDTO, @MappingTarget ListaValoresDetalle listaValoresDetalle);

    ResponseListaValoresDetalleDTO toDTO(ListaValoresDetalle listaValoresDetalle);

}

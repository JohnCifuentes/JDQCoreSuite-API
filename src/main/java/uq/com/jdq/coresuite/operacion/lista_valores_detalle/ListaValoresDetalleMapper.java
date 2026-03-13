package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapeador entre la entidad ListaValoresDetalle y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface ListaValoresDetalleMapper {

    /**
     * Convierte un DTO de creacion en entidad de detalle de lista de valores.
     * @param createListaValoresDetalleDTO datos de creacion.
     * @return entidad de detalle.
     */
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    ListaValoresDetalle toEntity(CreateListaValoresDetalleDTO createListaValoresDetalleDTO);

    /**
     * Actualiza una entidad de detalle de lista de valores a partir de un DTO.
     * @param updateListaValoresDetalleDTO datos actualizados.
     * @param listaValoresDetalle entidad a modificar.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateListaValoresDetalleDTO updateListaValoresDetalleDTO, @MappingTarget ListaValoresDetalle listaValoresDetalle);

    /**
     * Convierte una entidad de detalle en DTO de respuesta.
     * @param listaValoresDetalle entidad detalle.
     * @return DTO de respuesta.
     */
    ResponseListaValoresDetalleDTO toDTO(ListaValoresDetalle listaValoresDetalle);

}

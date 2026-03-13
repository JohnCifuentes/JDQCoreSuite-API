package uq.com.jdq.coresuite.operacion.lista_valores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapeador entre la entidad ListaValores y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface ListaValoresMapper {

    /**
     * Convierte un DTO de creacion en entidad lista de valores.
     * @param createListaValoresDTO datos de creacion.
     * @return entidad lista de valores.
     */
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    ListaValores toEntity(CreateListaValoresDTO createListaValoresDTO);

    /**
     * Actualiza una entidad lista de valores a partir de un DTO.
     * @param updateListaValoresDTO datos actualizados.
     * @param listaValores entidad a modificar.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateListaValoresDTO updateListaValoresDTO, @MappingTarget ListaValores listaValores);

    /**
     * Convierte una entidad lista de valores en DTO de respuesta.
     * @param listaValores entidad lista de valores.
     * @return DTO de respuesta.
     */
    ResponseListaValoresDTO toDTO(ListaValores listaValores);

}

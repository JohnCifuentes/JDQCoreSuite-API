package uq.com.jdq.coresuite.operacion.lista_valores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ListaValoresMapper {

    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    ListaValores toEntity(CreateListaValoresDTO createListaValoresDTO);

    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateListaValoresDTO updateListaValoresDTO, @MappingTarget ListaValores listaValores);

    ResponseListaValoresDTO toDTO(ListaValores listaValores);

}

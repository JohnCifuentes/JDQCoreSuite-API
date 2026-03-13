package uq.com.jdq.coresuite.operacion.campo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapeador entre la entidad Campo y sus DTO de entrada y salida.
 */
@Mapper(componentModel = "spring")
public interface CampoMapper {
    /**
     * Convierte un DTO de creacion en entidad campo.
     * @param createCampoDTO datos de creacion del campo.
     * @return entidad campo.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "interfaz", ignore = true)
    @Mapping(target = "interfaceGrupoCampos", ignore = true)
    @Mapping(target = "tipoCampo", ignore = true)
    @Mapping(target = "listaValores", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "usuarioActualizacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Campo toEntity(CreateCampoDTO createCampoDTO);

    /**
     * Actualiza una entidad campo a partir de un DTO de actualizacion.
     * @param updateCampoDTO datos actualizados del campo.
     * @param campo entidad campo a modificar.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "interfaz", ignore = true)
    @Mapping(target = "interfaceGrupoCampos", ignore = true)
    @Mapping(target = "tipoCampo", ignore = true)
    @Mapping(target = "listaValores", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateCampoDTO updateCampoDTO, @MappingTarget Campo campo);

    /**
     * Convierte una entidad campo en DTO de respuesta.
     * @param campo entidad campo.
     * @return DTO de respuesta del campo.
     */
    ResponseCampoDTO toDTO(Campo campo);
}

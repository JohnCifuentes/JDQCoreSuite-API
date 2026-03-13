package uq.com.jdq.coresuite.operacion.interfaz;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapeador entre la entidad Interfaz y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface InterfazMapper {
    /**
     * Convierte un DTO de creacion en entidad interfaz.
     * @param createInterfazDTO datos de creacion.
     * @return entidad interfaz.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modulo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "usuarioActualizacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Interfaz toEntity(CreateInterfazDTO createInterfazDTO);

    /**
     * Actualiza una entidad interfaz a partir de un DTO.
     * @param updateInterfazDTO datos actualizados.
     * @param interfaz entidad a modificar.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modulo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateInterfazDTO updateInterfazDTO, @MappingTarget Interfaz interfaz);

    /**
     * Convierte una entidad interfaz en DTO de respuesta.
     * @param interfaz entidad interfaz.
     * @return DTO de respuesta.
     */
    ResponseInterfazDTO toDTO(Interfaz interfaz);
}

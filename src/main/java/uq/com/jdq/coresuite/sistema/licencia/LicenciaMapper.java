package uq.com.jdq.coresuite.sistema.licencia;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper para convertir entre entidades de licencia y sus DTO.
 */
@Mapper(componentModel = "spring")
public interface LicenciaMapper {

    /**
     * Convierte el DTO de creacion en una entidad de licencia.
     * @param createLicenciaDTO datos de la licencia.
     * @return entidad lista para persistir.
     */
    @Mapping(target = "usuarioCreacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    Licencia toEntity(CreateLicenciaDTO createLicenciaDTO);

    /**
     * Actualiza una entidad de licencia con datos del DTO de edicion.
     * @param updateLicenciaDTO nuevos datos de la licencia.
     * @param licencia entidad que sera modificada.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void updateEntityFromDTO(UpdateLicenciaDTO updateLicenciaDTO, @MappingTarget Licencia licencia);

    /**
     * Aplica el cambio de estado sobre una licencia.
     * @param inactiveLicenciaDTO nuevo estado.
     * @param licencia entidad que sera inactivada.
     */
    @Mapping(target = "usuarioActualizacion", expression = "java(\"PRUEBAS\")")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.LocalDateTime.now())")
    void inactiveEntityFromDTO(InactiveLicenciaDTO inactiveLicenciaDTO, @MappingTarget Licencia licencia);

    /**
     * Convierte una entidad de licencia en su DTO de respuesta.
     * @param licencia entidad consultada.
     * @return DTO con la informacion de la licencia.
     */
    ResponseLicenciaDTO toDTO(Licencia licencia);

}

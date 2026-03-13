package uq.com.jdq.coresuite.catalogo.departamento;

import org.mapstruct.Mapper;

/**
 * Mapeador entre la entidad Departamento y su DTO.
 */
@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    /**
     * Convierte un DTO de departamento en entidad.
     * @param departamentoDTO datos del departamento.
     * @return entidad departamento.
     */
    Departamento toEntity(DepartamentoDTO departamentoDTO);

    /**
     * Convierte una entidad de departamento en DTO.
     * @param departamento entidad departamento.
     * @return DTO del departamento.
     */
    DepartamentoDTO toDTO(Departamento departamento);

}

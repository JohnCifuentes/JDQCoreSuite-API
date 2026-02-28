package uq.com.jdq.coresuite.sistema.sesion;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SesionMapper {

    Sesion toEntity(CreateSesionDTO createSesionDTO);

    void updateEntityFromDTO(UpdateSesionDTO updateSesionDTO, @MappingTarget Sesion sesion);

    void inactiveEntityFromDTO(InactiveSesionDTO inactiveSesionDTO, @MappingTarget Sesion sesion);

    ResponseSesionDTO toDTO(Sesion sesion);

}

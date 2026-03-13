package uq.com.jdq.coresuite.infra.authenticationeventstype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Define la estructura y comportamiento de class AuthenticationEventsTypeServiceImp.
 */
@RequiredArgsConstructor
@Service
public class AuthenticationEventsTypeServiceImp implements AuthenticationEventsTypeService {
    private final AuthenticationEventsTypeRepository authenticationEventsTypeRepository;
    private final AuthenticationEventsTypeMapper authenticationEventsTypeMapper;

    /**
     * Ejecuta la operacion getAll.
     * @return resultado de la operacion.
     */
    @Override
    public List<AuthenticationEventsTypeDTO> getAll() {
        return authenticationEventsTypeRepository.findAll().stream().map(authenticationEventsTypeMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Ejecuta la operacion getAuthenticationEventsTypeById.
     * @param id parametro de entrada.
     * @return resultado de la operacion.
     * @throws Exception en caso de error durante la operacion.
     */
    @Override
    public AuthenticationEventsType getAuthenticationEventsTypeById(Integer id) throws Exception {
        Optional<AuthenticationEventsType> authenticationEventsType = authenticationEventsTypeRepository.findById(id);
        if (authenticationEventsType.isPresent()) {
            return authenticationEventsType.get();
        } else {
            throw new NoExisteException("No se encontro el id de la eventa");
        }
    }

}

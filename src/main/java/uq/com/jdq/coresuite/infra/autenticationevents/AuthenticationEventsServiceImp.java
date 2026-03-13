package uq.com.jdq.coresuite.infra.autenticationevents;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.infra.authenticationeventstype.AuthenticationEventsTypeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Define la estructura y comportamiento de class AuthenticationEventsServiceImp.
 */
@RequiredArgsConstructor
@Service
public class AuthenticationEventsServiceImp implements AuthenticationEventsService {
    private final AuthenticationEventsRepository authenticationEventsRepository;
    private final AuthenticationEventsMapper authenticationEventsMapper;

    /**
     * Ejecuta la operacion createAuthenticationEvent.
     * @param authenticationEventsDTO parametro de entrada.
     * @return resultado de la operacion.
     * @throws Exception en caso de error durante la operacion.
     */
    @Override
    public AuthenticationEventsDTO createAuthenticationEvent(AuthenticationEventsDTO authenticationEventsDTO) throws Exception {
        AuthenticationEvents authenticationEvents = this.authenticationEventsRepository.save(this.authenticationEventsMapper.toEntity(authenticationEventsDTO));
        return authenticationEventsMapper.toDTO(authenticationEvents);
    }

    /**
     * Ejecuta la operacion getAll.
     * @return resultado de la operacion.
     */
    @Override
    public List<AuthenticationEventsDTO> getAll() {
        return this.authenticationEventsRepository.findAll().stream().map(authenticationEventsMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Ejecuta la operacion getAuthenticationEventsById.
     * @param id parametro de entrada.
     * @return resultado de la operacion.
     * @throws Exception en caso de error durante la operacion.
     */
    @Override
    public AuthenticationEventsDTO getAuthenticationEventsById(Long id) throws Exception {
        Optional<AuthenticationEvents> authenticationEvents = this.authenticationEventsRepository.findById(id);
        if(authenticationEvents.isPresent()) {
            return authenticationEventsMapper.toDTO(authenticationEvents.get());
        } else {
            throw new NoExisteException("No existe el registro en la aplicacion");
        }
    }

}

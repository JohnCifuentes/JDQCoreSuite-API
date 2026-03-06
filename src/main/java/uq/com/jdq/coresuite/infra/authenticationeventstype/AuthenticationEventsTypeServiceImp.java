package uq.com.jdq.coresuite.infra.authenticationeventstype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthenticationEventsTypeServiceImp implements AuthenticationEventsTypeService {
    private final AuthenticationEventsTypeRepository authenticationEventsTypeRepository;
    private final AuthenticationEventsTypeMapper authenticationEventsTypeMapper;

    @Override
    public List<AuthenticationEventsTypeDTO> getAll() {
        return authenticationEventsTypeRepository.findAll().stream().map(authenticationEventsTypeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AuthenticationEventsTypeDTO getAuthenticationEventsTypeById(Integer id) throws Exception {
        Optional<AuthenticationEventsType> authenticationEventsType = authenticationEventsTypeRepository.findById(Long.valueOf(id));
        if (authenticationEventsType.isPresent()) {
            return authenticationEventsTypeMapper.toDTO(authenticationEventsType.get());
        } else {
            throw new NoExisteException("No se encontro el id de la eventa");
        }
    }
}

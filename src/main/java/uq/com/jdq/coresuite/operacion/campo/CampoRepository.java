package uq.com.jdq.coresuite.operacion.campo;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.interfaz.Interfaz;

import java.util.List;
import java.util.Optional;

public interface CampoRepository extends JpaRepository<Campo, Long> {
    
    List<ResponseCampoDTO> findByInterfaz(Interfaz interfaz);
    
    Optional<Campo> findByInterfazAndNombre(Interfaz interfaz, String nombre);
    
    Optional<Campo> findByInterfazAndNombreAndIdNot(Interfaz interfaz, String nombre, Long id);
    
    Optional<Campo> findByInterfazAndIndice(Interfaz interfaz, Integer indice);
    
    Optional<Campo> findByInterfazAndIndiceAndIdNot(Interfaz interfaz, Integer indice, Long id);
    
}

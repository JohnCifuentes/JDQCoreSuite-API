package uq.com.jdq.coresuite.operacion.interfaz;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.modulo.Modulo;

import java.util.List;
import java.util.Optional;

public interface InterfazRepository extends JpaRepository<Interfaz, Long> {
    
    List<ResponseInterfazDTO> findByModulo(Modulo modulo);
    
    Optional<Interfaz> findByModuloAndNombre(Modulo modulo, String nombre);
    
    Optional<Interfaz> findByModuloAndNombreAndIdNot(Modulo modulo, String nombre, Long id);
    
    Optional<Interfaz> findByModuloAndIndice(Modulo modulo, Integer indice);
    
    Optional<Interfaz> findByModuloAndIndiceAndIdNot(Modulo modulo, Integer indice, Long id);
    
}

package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.interfaz.Interfaz;

import java.util.List;
import java.util.Optional;

public interface InterfaceGrupoCamposRepository extends JpaRepository<InterfaceGrupoCampos, Long> {
    
    List<ResponseInterfaceGrupoCamposDTO> findByInterfaz(Interfaz interfaz);
    
    Optional<InterfaceGrupoCampos> findByInterfazAndNombre(Interfaz interfaz, String nombre);
    
    Optional<InterfaceGrupoCampos> findByInterfazAndNombreAndIdNot(Interfaz interfaz, String nombre, Long id);
    
    Optional<InterfaceGrupoCampos> findByInterfazAndIndice(Interfaz interfaz, Integer indice);
    
    Optional<InterfaceGrupoCampos> findByInterfazAndIndiceAndIdNot(Interfaz interfaz, Integer indice, Long id);
    
}

package uq.com.jdq.coresuite.operacion.campo;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.interfaz.Interfaz;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Campo.
 */
public interface CampoRepository extends JpaRepository<Campo, Long> {
    
    /**
     * Lista los campos asociados a una interfaz.
     * @param interfaz entidad interfaz.
     * @return lista de campos de la interfaz.
     */
    List<ResponseCampoDTO> findByInterfaz(Interfaz interfaz);
    
    /**
     * Busca un campo por interfaz y nombre.
     * @param interfaz entidad interfaz.
     * @param nombre nombre del campo.
     * @return campo encontrado, si existe.
     */
    Optional<Campo> findByInterfazAndNombre(Interfaz interfaz, String nombre);
    
    /**
     * Busca un campo por interfaz y nombre excluyendo un identificador.
     * @param interfaz entidad interfaz.
     * @param nombre nombre del campo.
     * @param id identificador a excluir.
     * @return campo encontrado, si existe.
     */
    Optional<Campo> findByInterfazAndNombreAndIdNot(Interfaz interfaz, String nombre, Long id);
    
    /**
     * Busca un campo por interfaz e indice.
     * @param interfaz entidad interfaz.
     * @param indice indice del campo.
     * @return campo encontrado, si existe.
     */
    Optional<Campo> findByInterfazAndIndice(Interfaz interfaz, Integer indice);
    
    /**
     * Busca un campo por interfaz e indice excluyendo un identificador.
     * @param interfaz entidad interfaz.
     * @param indice indice del campo.
     * @param id identificador a excluir.
     * @return campo encontrado, si existe.
     */
    Optional<Campo> findByInterfazAndIndiceAndIdNot(Interfaz interfaz, Integer indice, Long id);
    
}

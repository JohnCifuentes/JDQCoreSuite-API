package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.lista_valores.ListaValores;

import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad ListaValoresDetalle.
 */
public interface ListaValoresDetalleRepository extends JpaRepository<ListaValoresDetalle, Long> {
    /**
     * Consulta los detalles asociados a una lista de valores.
     * @param listaValores entidad lista de valores.
     * @return lista de detalles relacionados.
     */
    List<ResponseListaValoresDetalleDTO> findByListaValores(ListaValores listaValores);
}

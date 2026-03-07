package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.lista_valores.ListaValores;

import java.util.List;

public interface ListaValoresDetalleRepository extends JpaRepository<ListaValoresDetalle, Long> {
    List<ResponseListaValoresDetalleDTO> findByListaValores(ListaValores listaValores);
}

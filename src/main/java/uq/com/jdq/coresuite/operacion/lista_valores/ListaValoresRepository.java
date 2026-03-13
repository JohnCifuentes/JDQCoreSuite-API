package uq.com.jdq.coresuite.operacion.lista_valores;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad ListaValores.
 */
public interface ListaValoresRepository extends JpaRepository<ListaValores, Long> {
    /**
     * Consulta las listas de valores asociadas a una empresa.
     * @param empresa entidad empresa.
     * @return lista de listas de valores relacionadas.
     */
    List<ResponseListaValoresDTO> findByEmpresa(Empresa empresa);

    /**
     * Busca una lista de valores por empresa y nombre.
     * @param empresa entidad empresa.
     * @param nombre nombre de la lista.
     * @return lista de valores encontrada, si existe.
     */
    Optional<ListaValores> findByEmpresaAndNombre(Empresa empresa, String nombre);

    /**
     * Busca una lista de valores por empresa y nombre excluyendo un identificador.
     * @param empresa entidad empresa.
     * @param nombre nombre de la lista.
     * @param id identificador a excluir.
     * @return lista de valores encontrada, si existe.
     */
    Optional<ListaValores> findByEmpresaAndNombreAndIdNot(Empresa empresa, String nombre, Long id);
}

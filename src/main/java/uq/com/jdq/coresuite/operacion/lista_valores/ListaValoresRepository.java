package uq.com.jdq.coresuite.operacion.lista_valores;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;
import java.util.Optional;

public interface ListaValoresRepository extends JpaRepository<ListaValores, Long> {
    List<ResponseListaValoresDTO> findByEmpresa(Empresa empresa);
    
    Optional<ListaValores> findByEmpresaAndNombre(Empresa empresa, String nombre);
    
    Optional<ListaValores> findByEmpresaAndNombreAndIdNot(Empresa empresa, String nombre, Long id);
}

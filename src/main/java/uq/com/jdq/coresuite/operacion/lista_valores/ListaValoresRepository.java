package uq.com.jdq.coresuite.operacion.lista_valores;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;

public interface ListaValoresRepository extends JpaRepository<ListaValores, Long> {
    List<ResponseListaValoresDTO> findByEmpresa(Empresa empresa);
}

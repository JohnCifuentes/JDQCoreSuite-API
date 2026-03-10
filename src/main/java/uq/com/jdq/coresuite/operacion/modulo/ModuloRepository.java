package uq.com.jdq.coresuite.operacion.modulo;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;
import java.util.Optional;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    List<ResponseModuloDTO> findByEmpresa(Empresa empresa);
    
    Optional<Modulo> findByEmpresaAndNombre(Empresa empresa, String nombre);
    
    Optional<Modulo> findByEmpresaAndNombreAndIdNot(Empresa empresa, String nombre, Long id);
    
    Optional<Modulo> findByEmpresaAndIndice(Empresa empresa, Integer indice);
    
    Optional<Modulo> findByEmpresaAndIndiceAndIdNot(Empresa empresa, Integer indice, Long id);

}

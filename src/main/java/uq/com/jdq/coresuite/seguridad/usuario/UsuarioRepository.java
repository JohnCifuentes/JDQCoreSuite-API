package uq.com.jdq.coresuite.seguridad.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<ResponseUsuarioDTO> findByEmpresa(Empresa empresa);

    Optional<Usuario> findByCorreoElectronicoAndPassword(String correoElectronico, String password) throws Exception;

    Optional<Usuario> findByCorreoElectronico(String correoElectronico) throws Exception;

}
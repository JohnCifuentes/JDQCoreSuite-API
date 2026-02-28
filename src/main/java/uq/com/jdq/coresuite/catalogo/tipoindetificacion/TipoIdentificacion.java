package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name="tipo_identificacion", schema = "catalogo")
public class TipoIdentificacion {
    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 2)
    private String codigo;

    @Column(nullable = false, unique = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = false, length = 1)
    private Boolean estado;

}

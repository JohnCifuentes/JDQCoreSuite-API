package uq.com.jdq.coresuite.catalogo.departamento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uq.com.jdq.coresuite.catalogo.pais.Pais;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "departamento", schema = "catalogo")
public class Departamento {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais pais;

    @Column(nullable = false, length = 10)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 10)
    private String abreviatura;

}

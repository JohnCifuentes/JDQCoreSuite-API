package uq.com.jdq.coresuite.catalogo.municipio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uq.com.jdq.coresuite.catalogo.departamento.Departamento;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "municipio", schema = "catalogo")
public class Municipio {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @Column(nullable = false, length = 10)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

}

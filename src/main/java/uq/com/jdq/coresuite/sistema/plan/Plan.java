package uq.com.jdq.coresuite.sistema.plan;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "plan", schema = "sistema")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_seq_generator")
    @SequenceGenerator(name = "plan_seq_generator", sequenceName = "sistema.plan_seq", allocationSize = 1)
    private Long id;

    @Column(name = "cantidad_usuarios", nullable = false)
    private Integer cantidadUsuarios;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "valor", precision = 12, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "estado", length = 1)
    private String estado = "A";

    @Column(name = "usuario_creacion", length = 50, nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", length = 50)
    private String usuarioActualizacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

}

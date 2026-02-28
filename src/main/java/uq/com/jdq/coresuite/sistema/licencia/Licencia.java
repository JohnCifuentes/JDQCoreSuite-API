package uq.com.jdq.coresuite.sistema.licencia;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.plan.Plan;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "licencia", schema = "sistema")
public class Licencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "licencia_seq_generator")
    @SequenceGenerator(name = "licencia_seq_generator", sequenceName = "sistema.licencia_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDate fechaExpiracion;

    @Column(name = "activo")
    private Boolean activo = true;

    @Column(name = "estado", length = 1)
    private String estado = "A";

    @Column(name = "usuario_creacion", length = 50, nullable = false, updatable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", length = 50)
    private String usuarioActualizacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

}

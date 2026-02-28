package uq.com.jdq.coresuite.seguridad.codigo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "codigo", schema = "seguridad")
public class Codigo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigo_seq_generator")
    @SequenceGenerator(name = "codigo_seq_generator", sequenceName = "seguridad.codigo_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "codigo", nullable = false, length = 255)
    private String codigo;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDateTime fechaGeneracion = LocalDateTime.now();

    @Column(name = "estado", nullable = false, length = 1)
    private String estado = "A";

}

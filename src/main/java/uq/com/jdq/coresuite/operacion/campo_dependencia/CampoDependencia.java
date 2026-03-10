package uq.com.jdq.coresuite.operacion.campo_dependencia;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uq.com.jdq.coresuite.operacion.campo.Campo;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "campo_dependencia", schema = "operacion")
public class CampoDependencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campo_dependencia_seq_generator")
    @SequenceGenerator(name = "campo_dependencia_seq_generator", sequenceName = "operacion.campo_dependencia_seq", allocationSize = 1)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "campo_id", nullable = false)
    public Campo campo;

    @ManyToOne
    @JoinColumn(name = "campo_dependiente_id", nullable = false)
    public Campo campoDependiente;

    @Column(name = "operador", nullable = false, length = 50)
    public String operador;

    @Column(name = "valor", length = 255)
    public String valor;

    @Column(name = "estado", nullable = false, length = 20)
    public String estado = "A";

    @Column(name = "usuario_creacion", nullable = false, length = 100)
    public String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    public LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", length = 100)
    public String usuarioActualizacion;

    @Column(name = "fecha_actualizacion")
    public LocalDateTime fechaActualizacion;

}

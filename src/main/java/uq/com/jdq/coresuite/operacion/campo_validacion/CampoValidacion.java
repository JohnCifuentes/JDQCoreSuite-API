package uq.com.jdq.coresuite.operacion.campo_validacion;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uq.com.jdq.coresuite.operacion.campo.Campo;
import uq.com.jdq.coresuite.operacion.tipo_validacion.TipoValidacion;

/**
 * Entidad que representa la asociacion entre un campo y una regla de validacion.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "campo_validacion", schema = "operacion")
public class CampoValidacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campo_validacion_seq_generator")
    @SequenceGenerator(name = "campo_validacion_seq_generator", sequenceName = "operacion.campo_validacion_seq", allocationSize = 1)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "campo_id", nullable = false)
    public Campo campo;

    @ManyToOne
    @JoinColumn(name = "tipo_validacion_id", nullable = false)
    public TipoValidacion tipoValidacion;

    @Column(name = "valor", length = 255)
    public String valor;

    @ManyToOne
    @JoinColumn(name = "campo_referencia_id")
    public Campo campoReferencia;

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

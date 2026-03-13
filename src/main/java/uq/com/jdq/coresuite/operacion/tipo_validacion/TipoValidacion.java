package uq.com.jdq.coresuite.operacion.tipo_validacion;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa un tipo de validacion configurable.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tipo_validacion", schema = "operacion")
public class TipoValidacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_validacion_seq_generator")
    @SequenceGenerator(name = "tipo_validacion_seq_generator", sequenceName = "operacion.tipo_validacion_seq", allocationSize = 1)
    public Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    public String nombre;

    @Column(name = "descripcion", length = 255)
    public String descripcion;

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

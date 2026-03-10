package uq.com.jdq.coresuite.operacion.interfaz;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uq.com.jdq.coresuite.operacion.modulo.Modulo;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "interfaz", schema = "operacion")
public class Interfaz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interfaz_seq_generator")
    @SequenceGenerator(name = "interfaz_seq_generator", sequenceName = "operacion.interfaz_seq", allocationSize = 1)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "modulo_id", nullable = false)
    public Modulo modulo;

    @Column(name = "nombre", nullable = false, length = 100)
    public String nombre;

    @Column(name = "descripcion", length = 255)
    public String descripcion;

    @Column(name = "indice")
    public Integer indice;

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

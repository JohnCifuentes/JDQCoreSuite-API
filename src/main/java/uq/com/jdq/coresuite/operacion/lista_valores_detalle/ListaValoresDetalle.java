package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uq.com.jdq.coresuite.operacion.lista_valores.ListaValores;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lista_valores_detalle", schema = "operacion")
public class ListaValoresDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lista_valores_detalle_seq_generator")
    @SequenceGenerator(name = "lista_valores_detalle_seq_generator", sequenceName = "operacion.lista_valores_detalle_seq", allocationSize = 1)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "lista_valores_id", nullable = false)
    public ListaValores listaValores;

    @Column(name = "nombre", nullable = false, length = 100)
    public String nombre;

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

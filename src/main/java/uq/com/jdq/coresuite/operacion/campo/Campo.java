package uq.com.jdq.coresuite.operacion.campo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uq.com.jdq.coresuite.operacion.interfaz.Interfaz;
import uq.com.jdq.coresuite.operacion.interface_grupo_campos.InterfaceGrupoCampos;
import uq.com.jdq.coresuite.operacion.tipo_campo.TipoCampo;
import uq.com.jdq.coresuite.operacion.lista_valores.ListaValores;

/**
 * Entidad que representa un campo configurable dentro de una interfaz operativa.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "campo", schema = "operacion")
public class Campo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campo_seq_generator")
    @SequenceGenerator(name = "campo_seq_generator", sequenceName = "operacion.campo_seq", allocationSize = 1)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "interfaz_id", nullable = false)
    public Interfaz interfaz;

    @ManyToOne
    @JoinColumn(name = "interface_grupo_campos_id")
    public InterfaceGrupoCampos interfaceGrupoCampos;

    @ManyToOne
    @JoinColumn(name = "tipo_campo_id", nullable = false)
    public TipoCampo tipoCampo;

    @ManyToOne
    @JoinColumn(name = "lista_valores_id")
    public ListaValores listaValores;

    @Column(name = "nombre", nullable = false, length = 100)
    public String nombre;

    @Column(name = "etiqueta", length = 150)
    public String etiqueta;

    @Column(name = "descripcion", length = 255)
    public String descripcion;

    @Column(name = "indice")
    public Integer indice;

    @Column(name = "columnas")
    public Integer columnas = 12;

    @Column(name = "valor_defecto", length = 255)
    public String valorDefecto;

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

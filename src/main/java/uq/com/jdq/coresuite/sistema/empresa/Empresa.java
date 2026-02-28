package uq.com.jdq.coresuite.sistema.empresa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uq.com.jdq.coresuite.catalogo.departamento.Departamento;
import uq.com.jdq.coresuite.catalogo.municipio.Municipio;
import uq.com.jdq.coresuite.catalogo.pais.Pais;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacion;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "empresa", schema = "sistema")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_seq_generator")
    @SequenceGenerator(name = "empresa_seq_generator", sequenceName = "sistema.empresa_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_identificacion_id", nullable = false)
    private TipoIdentificacion tipoIdentificacion;

    @ManyToOne
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
    private Municipio municipio;

    @Column(name = "numero_identificacion", length = 20, nullable = false, unique = true)
    private String numeroIdentificacion;

    @Column(name = "razon_social", length = 100, nullable = false)
    private String razonSocial;

    @Column(name = "direccion", length = 150, nullable = false)
    private String direccion;

    @Column(name = "correo_electronico", length = 100)
    private String correoElectronico;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "estado", length = 1, nullable = false)
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

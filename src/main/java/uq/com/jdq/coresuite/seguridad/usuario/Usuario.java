package uq.com.jdq.coresuite.seguridad.usuario;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacion;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario", schema = "seguridad")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq_generator")
    @SequenceGenerator(name = "usuario_seq_generator", sequenceName = "sistema.usuario_seq", allocationSize = 1)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    public Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "tipo_identificacion_id", nullable = false)
    public TipoIdentificacion tipoIdentificacion;

    @Column(name = "numero_identificacion", nullable = false, length = 20)
    public String numeroIdentificacion;

    @Column(name = "nombre1", nullable = false, length = 50)
    public String nombre1;

    @Column(name = "nombre2", length = 50)
    public String nombre2;

    @Column(name = "apellido1", nullable = false, length = 50)
    public String apellido1;

    @Column(name = "apellido2", length = 50)
    public String apellido2;

    @Column(name = "telefono", nullable = false, length = 50)
    public String telefono;

    @Column(name = "correo_electronico", nullable = false, length = 100)
    public String correoElectronico;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "primer_acceso", nullable = false)
    public boolean primerAcceso = true;

    @Column(name = "estado", nullable = false, length = 1)
    public String estado = "A";

    @Column(name = "usuario_creacion", nullable = false, length = 50)
    public String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    public LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", length = 50)
    public String usuarioActualizacion;

    @Column(name = "fecha_actualizacion")
    public LocalDateTime fechaActualizacion;

}

package uq.com.jdq.coresuite.catalogo.pais;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "pais", schema = "catalogo")
public class Pais {
    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String codigo;

    @Column(nullable = false, unique = true, length = 3)
    private String iso3;

    @Column(nullable = false, length = 100)
    private String nombre;

}

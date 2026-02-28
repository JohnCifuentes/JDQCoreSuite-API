package uq.com.jdq.coresuite.catalogo.municipio;

import uq.com.jdq.coresuite.catalogo.departamento.Departamento;

public record MunicipioDTO(
        Long id,
        Departamento departamento,
        String codigo,
        String nombre
) {
}

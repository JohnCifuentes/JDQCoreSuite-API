package uq.com.jdq.coresuite.catalogo.departamento;

import uq.com.jdq.coresuite.catalogo.pais.Pais;

/**
 * DTO de salida para transferir informacion de departamentos.
 */
public record DepartamentoDTO(
        Long id,
        Pais pais,
        String codigo,
        String nombre,
        String abreviatura
) {
}

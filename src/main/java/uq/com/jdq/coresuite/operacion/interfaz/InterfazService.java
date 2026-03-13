package uq.com.jdq.coresuite.operacion.interfaz;

import java.util.List;

/**
 * Contrato de negocio para la administracion y consulta de interfaces.
 */
public interface InterfazService {

    /**
     * Crea una nueva interfaz.
     * @param createInterfazDTO datos de creacion.
     * @return interfaz creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    ResponseInterfazDTO createInterfaz(CreateInterfazDTO createInterfazDTO) throws Exception;

    /**
     * Actualiza una interfaz existente.
     * @param id identificador de la interfaz.
     * @param updateInterfazDTO datos actualizados.
     * @return interfaz actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    ResponseInterfazDTO updateInterfaz(Long id, UpdateInterfazDTO updateInterfazDTO) throws Exception;

    /**
     * Obtiene todas las interfaces registradas.
     * @return lista de interfaces.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseInterfazDTO> getAllInterfaz() throws Exception;

    /**
     * Obtiene una interfaz por identificador.
     * @param id identificador de la interfaz.
     * @return interfaz encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    ResponseInterfazDTO getInterfazById(Long id) throws Exception;

    /**
     * Obtiene las interfaces asociadas a un modulo.
     * @param moduloId identificador del modulo.
     * @return lista de interfaces relacionadas.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseInterfazDTO> getInterfazByModulo(Long moduloId) throws Exception;

}

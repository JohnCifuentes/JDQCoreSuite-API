package uq.com.jdq.coresuite.sistema.sesion;

import uq.com.jdq.coresuite.seguridad.usuario.Usuario;

import java.util.List;

/**
 * Contrato de negocio para la gestion de sesiones.
 */
public interface SesionService {

    /**
     * Registra una nueva sesion.
     * @param createSesionDTO datos de la sesion.
     * @return sesion creada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseSesionDTO createSesion(CreateSesionDTO createSesionDTO) throws Exception;

    /**
     * Inactiva la sesion activa de un usuario.
     * @param usuario usuario propietario de la sesion.
     * @throws Exception si ocurre un error de negocio.
     */
    void inactiveSesion(Usuario usuario) throws Exception;

    /**
     * Lista todas las sesiones registradas.
     * @return lista de sesiones.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseSesionDTO> getAllSesiones() throws Exception;

    /**
     * Consulta una sesion por identificador.
     * @param id identificador de la sesion.
     * @return sesion encontrada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseSesionDTO getSesionById(Long id) throws Exception;

    /**
     * Lista las sesiones asociadas a una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de sesiones.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseSesionDTO> getSesionesByEmpresa(Long empresaId) throws Exception;

}

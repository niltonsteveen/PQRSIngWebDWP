package co.pqrs.ing.web.logic;

import java.util.List;

import co.pqrs.ing.web.db.SolicitudPQR;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Interfaz encargada de definir los metodos o acciones correspondientes a una Solicitud de PQR
 */
public interface SolicitudPQRBl {
	/**
	 * @param solicitudPQR: Objeto de la clase SolicitudPQR
	 * @param usuario: Objeto de la clase Usuario
	 * @throws MyDAOException
	 * Crea una Solicitud (PQR)
	 */
	public void crearPQR(SolicitudPQR solicitudPQR,Usuario usuario)throws MyDAOException;
	/**
	 * @param solicitudPQR: Objeto de la clase SolicitudPQR
	 * @param usuario: Objeto de la clase Usuario
	 * @throws MyDAOException
	 * Cancela una solicitud (PQR)
	 */
	public void cancelarPQR(SolicitudPQR solicitudPQR,Usuario usuario)throws MyDAOException;
	/**
	 * @param usuario: Objeto de la clase Usuario
	 * @return Retorna una lista de objetos de la clase SolicitudPQR
	 * @throws MyDAOException
	 * Organizar todas las PQR no atendidas por fecha y asigna la fecha de respuesta
	 */
	public List<SolicitudPQR> notificarPQR(Usuario usuario)throws MyDAOException;
	/**
	 * @param id: Identificador de una Solicitud (PQR)
	 * @return Retorna un objeto de la clase SolicitudPQR
	 * @throws MyDAOException
	 * Busca una solicitud por medio de su identificador
	 */
	public SolicitudPQR getSolicitudById(Long id) throws MyDAOException;
	/**
	 * @param solicitud: Identificador de una Solicitud (PQR)
	 * @param encargado: Objeto de la clase Usuario
	 * @throws MyDAOException
	 * Delega una solicitud a un encargado que se encuentre habilitado en el sistema
	 */
	public void delegarPQR(Long solicitud, Usuario encargado, Usuario loged) throws MyDAOException;
	/**
	 * @param solicitud: Identificador de una solicitud (PQR)
	 * @param encargado: Objeto de la clase Usuario
	 * @param respuesta: Respuesta que se le dará a la solicitud del usuario
	 * @throws MyDAOException
	 * Envia la respuesta de una solicitud a un usuario 
	 */
	public void responderPQR(Long solicitud, Usuario encargado, String respuesta) throws MyDAOException;
}

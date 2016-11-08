package co.pqrs.ing.web.logic;

import co.pqrs.ing.web.db.EncuestaSatisfaccion;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Interfaz encargada de definir los metodos o acciones correspondientes a una encuesta de satisfaccion
 */
public interface EncuestaSatisfaccionBI {
	/**
	 * @param encuesta: Objeto de la clase encuesta
	 * @return void
	 * @throws MyDAOException Cuando se hace referencia a objetos nulos
	 * @throws MyDAOException UnsupportedEncodingException: Cuando ocurre un error enviando la respuesta
	 */
	public void enviarEncuestaSatisfaccion(EncuestaSatisfaccion encuesta) throws MyDAOException;
	/**
	 * @param encuesta: Objeto de la clase EncuestaSatisfaccion
	 * @return Long: id de la encuesta guardada
	 * @throws MyDAOException: cuando se hace referencia a objetos nulos
	 */
	public Long guardarEncuesta(EncuestaSatisfaccion encuesta) throws MyDAOException;
	/**
	 * @param identificadorEncuesta: tipo Long con el id o PK de la encuesta
	 * @return Un objeto de la clase EncuestaSatisfaccion con todas las preguntas asociadas
	 * @throws MyDAOException: Cuando se hace referencia a un objeto nulo
	 */
	public EncuestaSatisfaccion presentarEncuesta(Long identificadorEncuesta) throws MyDAOException;
	/**
	 * @param idEncuesta: Identificador o PK de una encuesta
	 * @return Un objeto de la clase EncuestaSatisfaccion dado su id
	 * @throws MyDAOException: Cuando se hace referencia a un objeto nulo
	 */
	public EncuestaSatisfaccion cargarEncuesta(Long idEncuesta) throws MyDAOException;
}

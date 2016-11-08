package co.pqrs.ing.web.logic;

import java.util.List;

import co.pqrs.ing.web.db.EncuestaSatisfaccion;
import co.pqrs.ing.web.db.Respuesta;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Interfaz encargada de definir los metodos o acciones correspondientes a una Respuesta
 */
public interface RespuestasBI {
		/**
		 * @param respuestas: Lista de Objetos de la clase Respuestas
		 * @throws MyDAOException
		 * Guarda una lista de respuestas
		 */
		public void guardarRespuestas(List<Respuesta> respuestas) throws MyDAOException;
		/**
		 * @param encuesta: Objeto de la clase EncuestaSatisfaccion
		 * @throws MyDAOException
		 * Lista todas las respuestas de una encuesta
		 */
		public void listarRespuestasByEncuesta(EncuestaSatisfaccion encuesta) throws MyDAOException;	
}

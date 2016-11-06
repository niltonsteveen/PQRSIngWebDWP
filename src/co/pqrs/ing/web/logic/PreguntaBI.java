package co.pqrs.ing.web.logic;

import java.util.List;

import co.pqrs.ing.web.db.PlantillaEncuesta;
import co.pqrs.ing.web.db.Pregunta;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Interfaz encargada de definir los metodos o acciones correspondientes a una Pregunta
 */
public interface PreguntaBI {
	/**
	 * @param plantilla: Objeto de la clase PlantillaEncuesta
	 * @return Retorna una lista de preguntas que seran asignadas a la plantilla
	 * @throws MyDAOException
	 */
	public List<Pregunta> listarPreguntasByPlantilla(PlantillaEncuesta plantilla) throws MyDAOException;
	/**
	 * @param pregunta: Objeto de la clase Pregunta
	 * @param plantilla: Objeto de la clase PlantillaEncuesta
	 * @throws MyDAOException
	 * Agrega una pregunta a la plantilla seleccionada
	 */
	public void agregarPregunta(Pregunta pregunta, PlantillaEncuesta plantilla) throws MyDAOException;
	/**
	 * @param pregunta: Objeto de la clase Pregunta
	 * @throws MyDAOException
	 * Modifica una pegunta
	 */
	public void modificarPregunta(Pregunta pregunta) throws MyDAOException;
	/**
	 * @param pregunta: Objeto de la clase Pregunta
	 * @throws MyDAOException
	 * Desahibilita una pregunta
	 */
	public void deshabilitarPregunta(Pregunta pregunta) throws MyDAOException;
	

}

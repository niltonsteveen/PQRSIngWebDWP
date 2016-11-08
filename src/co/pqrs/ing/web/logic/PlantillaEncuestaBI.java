package co.pqrs.ing.web.logic;

import java.util.List;

import co.pqrs.ing.web.db.PlantillaEncuesta;
import co.pqrs.ing.web.enums.TipoPQR;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com
 * @version 1.0.0
 * Interfaz encargada de definir los metodos o acciones correspondientes a una Plantilla de encuesta 
 */
public interface PlantillaEncuestaBI {
	/**
	 * @param plantilla: Objeto de la clase PlantillaEncuesta
	 * @return Void
	 * @throws MyDAOException Cuando se hace referencia a un objeto o campo nulo
	 * Metodo encargado de insertar una plantilla en la base de datos
	 */
	public void crearPlantillaEncuesta(PlantillaEncuesta plantilla) throws MyDAOException;
	/**
	 * @param plantilla: Objeto de la clase PlantillaEncuesta
	 * @return Void
	 * @throws MyDAOException Cuando se hace referencia a un objeto o campo nulo
	 * Metodo encargado de modificar una plantilla en la base de datos
	 */
	public void modificarPlantillaEncuesta(PlantillaEncuesta plantilla) throws MyDAOException;
	/**
	 * @param id: identificador de la plantilla
	 * @return PlantillaEncuesta
	 * @throws MyDAOException Cuando se hace referencia a un objeto o campo nulo
	 * Obtiene la plantilla solicitada dado su identificador
	 */
	public PlantillaEncuesta obtenerPlantilla(Long id) throws MyDAOException;
	/**
	 * @return Retorna una lista de plantillas
	 * @throws MyDAOException Cuando se hace referencia a un objeto o campo nulo
	 */
	public List<PlantillaEncuesta> listarPlantillas() throws MyDAOException;
	/**
	 * @param tipo: Objeto de la clase TipoPQR
	 * @return Retorna una plantilla de encuesta
	 * @throws MyDAOException Cuando se hace referencia a un objeto o campo nulo
	 */
	public PlantillaEncuesta obtenerPorTipo(TipoPQR tipo) throws MyDAOException;
	
	
}

package co.pqrs.ing.web.logic;

import java.util.List;

import co.pqrs.ing.web.db.Sucursal;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.exception.MyDAOException;
/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com
 * @version 1.0.0
 * Interfaz encargada de definir los metodos o acciones correspondientes a una Sucursal
 */
public interface SucursalBl {
	/**
	 * @param id
	 * @return
	 * @throws MyDAOException
	 */
	public Sucursal getSucursalById(Long id)throws MyDAOException;
	/**
	 * @return
	 * @throws MyDAOException
	 */
	public List<Sucursal> toList()throws MyDAOException;
	/**
	 * @param sucursal
	 * @throws MyDAOException
	 */
	public void crearSucursal(Sucursal sucursal) throws MyDAOException;
	/**
	 * @param sucursal
	 * @throws MyDAOException
	 */
	public void actualizarSucursal(Long sucursal) throws MyDAOException;
	
}

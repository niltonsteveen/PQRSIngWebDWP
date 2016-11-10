package co.pqrs.ing.web.logic;

import java.util.List;

import co.pqrs.ing.web.db.Sucursal;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.exception.MyDAOException;

public interface SucursalBl {

	public Sucursal getSucursalById(Long id)throws MyDAOException;
	
	public List<Sucursal> toList()throws MyDAOException;
	
	public void crearSucursal(Sucursal sucursal) throws MyDAOException;
	
	public void actualizarSucursal(Long sucursal) throws MyDAOException;
	
}

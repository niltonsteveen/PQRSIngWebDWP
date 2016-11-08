package co.pqrs.ing.web.logic;

import co.pqrs.ing.web.db.Sucursal;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.exception.MyDAOException;

public interface SucursalBl {

	public Sucursal getSucursalById(Long id)throws MyDAOException;
}

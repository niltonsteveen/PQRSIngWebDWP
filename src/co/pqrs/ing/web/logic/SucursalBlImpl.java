package co.pqrs.ing.web.logic;

import co.pqrs.ing.web.dao.impl.SucursalDAO;
import co.pqrs.ing.web.db.Sucursal;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.exception.MyDAOException;

public class SucursalBlImpl implements SucursalBl {
	
	SucursalDAO sucursalDao;

	public SucursalDAO getSucursalDao() {
		return sucursalDao;
	}
	public void setSucursalDao(SucursalDAO sucursalDao) {
		this.sucursalDao = sucursalDao;
	}



	@Override
	public Sucursal getSucursalById(Long id) throws MyDAOException {
		Sucursal sucursal;
		sucursal=sucursalDao.toGet(id);
		return sucursal;
	}

}

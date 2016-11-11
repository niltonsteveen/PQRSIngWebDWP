package co.pqrs.ing.web.logic;

import java.util.List;

import co.pqrs.ing.web.dao.impl.SucursalDAO;
import co.pqrs.ing.web.db.Sucursal;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.util.Utils;
/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com
 * @version 1.0.0
 * Clase que implementa los metodos de la interfaz SucursalBI
 */
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
		if(sucursal==null){
			throw new MyDAOException("La sucursal no existe");
		}
		return sucursal;
	}
	@Override
	public List<Sucursal> toList() throws MyDAOException {
		List<Sucursal> sucursales=null;
		sucursales=sucursalDao.toList();
		if(sucursales==null){
			throw new MyDAOException("No hay sucursales creadas en la base de datos");
		}else{
			return sucursales;
		}
	}
	@Override
	public void crearSucursal(Sucursal sucursal) throws MyDAOException {
		if(sucursal==null){
			throw new MyDAOException("La sucursal no puede ser nula");
		}
		sucursalDao.toSave(sucursal);
		
	}
	@Override
	public void actualizarSucursal(Long sucursal) throws MyDAOException {
		if(sucursal==null){
			throw new MyDAOException("La sucursal no puede ser nula");
		}
		if(sucursalDao.toGet(sucursal)==null){
			throw new MyDAOException("la sucursal no existe");
		}
		
		sucursalDao.toUpdate(sucursalDao.toGet(sucursal));
	}

	
}

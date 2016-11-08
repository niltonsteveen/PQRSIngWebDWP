package co.pqrs.ing.web.logic;

import java.util.List;

import co.pqrs.ing.web.dao.impl.PlantillaEncuestaDAO;
import co.pqrs.ing.web.db.PlantillaEncuesta;
import co.pqrs.ing.web.enums.TipoPQR;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que implementa los metodos de la interfaz PlantillaEncuestaBI 
 */
public class PlantillaEncuestaBlImpl implements PlantillaEncuestaBI {

	private PlantillaEncuestaDAO plantillaDao;
	
	
	public PlantillaEncuestaDAO getPlantillaDao() {
		return plantillaDao;
	}

	public void setPlantillaDao(PlantillaEncuestaDAO plantillaDao) {
		this.plantillaDao = plantillaDao;
	}
	
	@Override
	public void crearPlantillaEncuesta(PlantillaEncuesta plantilla) throws MyDAOException {
		// TODO Auto-generated method stub
		if(plantilla==null){
			throw new MyDAOException("La plantilla no puede ser nula");
		}else if(plantilla.getNombre()==null){
			throw new MyDAOException("La plantilla debe tener un nombre");
		}else if(this.plantillaDao.getByTipo(plantilla.getTipo())!=null){
			throw new MyDAOException("La plantilla para ese tipo de PQR ya esta definida");
		}else{
			plantillaDao.toSave(plantilla);
		}
	}
	
	@Override
	public void modificarPlantillaEncuesta(PlantillaEncuesta plantilla) throws MyDAOException {
		// TODO Auto-generated method stub
		if(plantilla==null){
			throw new MyDAOException("La plantilla no puede ser nula");
		}else if(plantilla.getNombre()== null){
			throw new MyDAOException("La plantilla debe tener un nombre");
		}else if(plantilla.getCodigo()== null){
			throw new MyDAOException("El codigo de la plantilla no puede ser nulo");
		}else if(this.plantillaDao.toGet(plantilla.getCodigo())==null){
			throw new MyDAOException("La plantilla que desea actualizar no existe");
		}else if(!plantilla.getCodigo().equals(this.plantillaDao.getByTipo(plantilla.getTipo()).getCodigo())){
			throw new MyDAOException("El Tipo de PQR no es posible Modificarlo");
		}else{
			plantillaDao.toUpdate(plantilla);
		}
	}

	@Override
	public PlantillaEncuesta obtenerPlantilla(Long id) throws MyDAOException {
		// TODO Auto-generated method stub
		if(id==null){
			throw new MyDAOException("El id de la plantilla no puede ser nulo");
		}
		return plantillaDao.toGet(id);
	}

	@Override
	public List<PlantillaEncuesta> listarPlantillas() throws MyDAOException {
		// TODO Auto-generated method stub
		return plantillaDao.toList();
	}

	@Override
	public PlantillaEncuesta obtenerPorTipo(TipoPQR tipo) throws MyDAOException {
		// TODO Auto-generated method stub
		if(tipo==null){
			throw new MyDAOException("El tipo no puede ser nulo");
		}
		return plantillaDao.getByTipo(tipo);
	}
	
}

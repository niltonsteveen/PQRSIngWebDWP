package co.pqrs.ing.web.logic;

import java.util.List;

import co.pqrs.ing.web.dao.impl.PreguntaDAO;
import co.pqrs.ing.web.db.PlantillaEncuesta;
import co.pqrs.ing.web.db.Pregunta;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que implementa los metodos de la interfaz PreguntaBI 
 */
public class PreguntaBlImpl implements PreguntaBI {

	PreguntaDAO preguntaDao;
	
	
	
	public PreguntaDAO getPreguntaDao() {
		return preguntaDao;
	}

	public void setPreguntaDao(PreguntaDAO preguntaDao) {
		this.preguntaDao = preguntaDao;
	}

	@Override
	public List<Pregunta> listarPreguntasByPlantilla(PlantillaEncuesta plantilla) throws MyDAOException {
		// TODO Auto-generated method stub
		if(plantilla==null){
			throw new MyDAOException("La plantilla no puede ser nula", new NullPointerException());
		}else if(plantilla.getCodigo()==null){
			throw new MyDAOException("El codigo de la plantilla no puede ser nulo", new NullPointerException());
		}else{
			return preguntaDao.getByPlantilla(plantilla);
		}
	}

	@Override
	public void agregarPregunta(Pregunta pregunta) throws MyDAOException {
		// TODO Auto-generated method stub
		 if(pregunta==null){
			throw new MyDAOException("La pregunta no puede ser nula", new NullPointerException());
		}else if(pregunta.getCodigo()==null){
			throw new MyDAOException("El codigo de la plantilla no puede ser nulo", new NullPointerException());
		}else if(pregunta.getPregunta()==null){
			throw new MyDAOException("La pregunta no esta definida debe definir la pregunta", new NullPointerException());
		}if(pregunta.getPlantilla()==null){
			throw new MyDAOException("La plantilla no puede ser nula", new NullPointerException());
		}else{
			preguntaDao.toSave(pregunta);
		}
	}

	@Override
	public void modificarPregunta(Pregunta pregunta) throws MyDAOException {
		// TODO Auto-generated method stub
		if(pregunta==null){
			throw new MyDAOException("La pregunta no puede ser nula", new NullPointerException());
		}else if(pregunta.getPregunta()==null){
			throw new MyDAOException("La pregunta no esta definida debe definir la pregunta", new NullPointerException());
		}else if(pregunta.getCodigo()==null){
			throw new MyDAOException("El codigo no puede ser nulo", new NullPointerException());
		}else if(preguntaDao.toGet(pregunta.getCodigo())==null){
			throw new MyDAOException("La pregunta no existe", new NullPointerException());
		}else{
			preguntaDao.toUpdate(pregunta);
		}	
	}

	@Override
	public void deshabilitarPregunta(Pregunta pregunta) throws MyDAOException {
		// TODO Auto-generated method stub
		
		if(pregunta==null){
			throw new MyDAOException("La pregunta no puede ser nula", new NullPointerException());
		}else if(pregunta.getCodigo()==null){
			throw new MyDAOException("El codigo no puede ser nulo", new NullPointerException());
		}else{
			Pregunta preg = preguntaDao.toGet(pregunta.getCodigo());
			if(preg==null){
				throw new MyDAOException("La pregunta no existe", new NullPointerException());
			}else{
				preg.setHabilitada(Boolean.FALSE);
				preguntaDao.toUpdate(preg);
			}	
		}
		
		
	}

}

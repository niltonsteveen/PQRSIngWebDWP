package co.pqrs.ing.web.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import co.pqrs.ing.web.dao.DAOGeneric;
import co.pqrs.ing.web.db.PlantillaEncuesta;
import co.pqrs.ing.web.db.Pregunta;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandros.erna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que hereda de DAOGeneric e implementa todos sus metodos y otros propios
 */
public class PreguntaDAO extends DAOGeneric<Pregunta>{

	/**
	 * 
	 * @param plantilla: Objeto de la clase PlantillaEncuesta
	 * @return Retorna una lista de preguntas dada una plantilla
	 * @throws MyDAOException: Cuando se genera algun problema en la consulta
	 */
	public List<Pregunta> getByPlantilla(PlantillaEncuesta plantilla) throws MyDAOException {
		Session session = null;
		List<Pregunta> preguntas;
		try{
			session = getSessionFactory().openSession();
			Criteria criteria =session.createCriteria(Pregunta.class).add(Restrictions.eq("plantilla", plantilla));
			preguntas = criteria.list();
			
		}catch (HibernateException e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}
		return preguntas ;
	}
	
}

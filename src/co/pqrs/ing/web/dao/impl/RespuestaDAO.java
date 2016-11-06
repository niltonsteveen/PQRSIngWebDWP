package co.pqrs.ing.web.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import co.pqrs.ing.web.dao.DAOGeneric;
import co.pqrs.ing.web.db.EncuestaSatisfaccion;
import co.pqrs.ing.web.db.PlantillaEncuesta;
import co.pqrs.ing.web.db.Respuesta;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que hereda de DAOGeneric e implementa todos sus metodos y otros propios
 */
public class RespuestaDAO extends DAOGeneric<Respuesta>{
	/**
	 * 
	 * @param encuesta: Objeto de la clase EncuestaSatisfaccion 
	 * @return Retorna una lista de respuestas dada una encuesta
	 * @throws MyDAOException: Cuando se genera algun problema en la consulta
	 */
	public List<Respuesta> getByEncuesta(EncuestaSatisfaccion encuesta) throws MyDAOException {
		Session session = null;
		List<Respuesta> respuestas;
		try{
			session = getSessionFactory().openSession();
			Criteria criteria =session.createCriteria(PlantillaEncuesta.class).add(Restrictions.eq("encuesta", encuesta));
			respuestas = criteria.list();
		}catch (HibernateException e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}
		return respuestas ;
	} 
	
}

package co.pqrs.ing.web.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import co.pqrs.ing.web.dao.DAOGeneric;
import co.pqrs.ing.web.db.PlantillaEncuesta;
import co.pqrs.ing.web.enums.TipoPQR;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que hereda de DAOGeneric e implementa todos sus metodos y otros propios
 */ 
public class PlantillaEncuestaDAO extends DAOGeneric<PlantillaEncuesta>{

	/**
	 * 
	 * @param tipo: Ojeto de la clase TipoPQR
	 * @return Retorna un objeto de la clase plantilla encuesta dado el tipo de solicitud
	 * @throws MyDAOException: Cuando se genera algun problema en la consulta
	 */
	public PlantillaEncuesta getByTipo(TipoPQR tipo) throws MyDAOException {
		Session session = null;
		PlantillaEncuesta plantilla=null;
		try{
			session = getSessionFactory().openSession();
			Criteria criteria =session.createCriteria(PlantillaEncuesta.class).add(Restrictions.eq("tipo", tipo));
			plantilla = (PlantillaEncuesta)criteria.uniqueResult();
			
		}catch (HibernateException e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}
		return plantilla ;
	}
}

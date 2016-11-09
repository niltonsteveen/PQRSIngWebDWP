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
import co.pqrs.ing.web.db.SolicitudPQR;
import co.pqrs.ing.web.exception.MyDAOException;
/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que hereda de DAOGeneric e implementa todos sus metodos
 */
public class EncuestaSatisfaccionDAO extends DAOGeneric<EncuestaSatisfaccion>{
	
	public EncuestaSatisfaccion getEncuestaBySolicitud(Long solicitudId) throws MyDAOException{
		Session session = null;
		try{
			session = getSessionFactory().openSession();
			SolicitudPQR solicitud = new SolicitudPQR();
			solicitud.setIdentificador(solicitudId);
			Criteria criteria =session.createCriteria(EncuestaSatisfaccion.class).add(Restrictions.eq("solicitud", solicitud));
			EncuestaSatisfaccion encuesta = (EncuestaSatisfaccion) criteria.uniqueResult();
			return encuesta;
		}catch (HibernateException e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}
	}
}

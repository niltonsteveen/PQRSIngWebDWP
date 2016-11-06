package co.pqrs.ing.web.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import co.pqrs.ing.web.dao.DAOGeneric;
import co.pqrs.ing.web.db.SolicitudPQR;
import co.pqrs.ing.web.enums.EstadoPQR;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que hereda de DAOGeneric e implementa todos sus metodos y otros propios
 */

public class SolicitudPQRDAO extends DAOGeneric<SolicitudPQR>{
	
	/**
	 * @return Retorna una lista de solicitudes con el estado sin atender ordenados ascendentemente
	 * por fecha de creacion y se asigna la fecha de resolucion de dicha solicitud 
	 * @throws MyDAOException Hibernate: Cuando se genera algun problema en la consulta 
	 */
	public List<SolicitudPQR> toListByState() throws MyDAOException{
		Session session = null;
		List<SolicitudPQR> notificaciones;
		try{
			session = getSessionFactory().openSession();
			Criteria criteria =session.createCriteria(SolicitudPQR.class);
			criteria.add(Restrictions.eq("estado", EstadoPQR.SIN_ATENDER));
			criteria.addOrder(Order.asc("fechaCreacion"));
			notificaciones=criteria.list();
			Calendar calendar=Calendar.getInstance();
			SolicitudPQR pqr;
			Date fechaCreacion;
			for(int i=0;i<notificaciones.size();i++){	
				pqr = notificaciones.get(i);
				fechaCreacion = pqr.getFechaCreacion();
				calendar.setTime(fechaCreacion);
				calendar.add(Calendar.DAY_OF_YEAR, 15);
				pqr.setFechaAtencion(calendar.getTime());
			}
			
		}catch (HibernateException e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}
		return notificaciones;
	}
}

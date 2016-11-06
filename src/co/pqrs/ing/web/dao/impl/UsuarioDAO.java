package co.pqrs.ing.web.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import co.pqrs.ing.web.dao.DAOGeneric;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que hereda de DAOGeneric e implementa todos sus metodos y otros propios
 */
public class UsuarioDAO extends DAOGeneric<Usuario> {
	
	/**
	 * 
	 * @param email: email del usuario
	 * @return Retorna un objeto de la clase usuario dado el correo del usuario
	 * @throws MyDAOException: Cuando se genera algun problema en la consulta
	 */
	public Usuario getByEmail(String email) throws MyDAOException {
		Session session = null;
		Usuario user=null;
		try{
			session = getSessionFactory().openSession();
			Criteria criteria =session.createCriteria(Usuario.class).add(Restrictions.eq("email", email));
			user = (Usuario)criteria.uniqueResult();
			
		}catch (HibernateException e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}
		return user;
	}

}

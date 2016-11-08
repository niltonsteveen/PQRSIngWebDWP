package co.pqrs.ing.web.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import co.pqrs.ing.web.exception.MyDAOException;
/**
 * 
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 *
 * @param <T> Este parametro es una clase tipo DAO que usará los metodos de esta clase abstracta
 * @version 1.0.0
 */
public abstract class DAOGeneric<T> {

	private Class<T> clase;
	private SessionFactory sessionFactory;

	public DAOGeneric() {
		try {
			this.clase = (Class<T>) ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass())
					.getActualTypeArguments()[0]);
		} catch (ClassCastException e) {

		}

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return Retorna una lista con todos los objetos de la clase t
	 * @throws MyDAOException: Cuando se genera algun problema en la consulta
	 */
	public List<T> toList() throws MyDAOException {

		// TODO Auto-generated method stub
		Session session = null;
		List<T> objs = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(clase);
			objs = criteria.list();

		} catch (HibernateException e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}finally{
			if(session!=null &&(session.isOpen()|| session.isConnected()) ){
				session.close();
			}
		}
		return objs;

	}
	/**
	 * Metodo que obtiene un objeto de la clase T
	 * @param id del objeto T
	 * @return Retorna un objeto de la clase T
	 * @throws MyDAOException: Cuando se genera algun problema en la consulta
	 */
	public T toGet(Serializable id) throws MyDAOException {
		// TODO Auto-generated method stub
		Session session = null;
		T obj = null;
		try {
			session = sessionFactory.openSession();
			obj = (T) session.get(clase, id);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}finally{
			if(session!=null &&(session.isOpen()|| session.isConnected()) ){
				session.close();
			}
		}
		return obj;
	}
	
	/**
	 * Metodo que guarda un objeto de la clase T
	 * @param param: Este parametro es un objeto de la clase T
	 * @throws MyDAOException: Cuando se genera algun problema en la consulta
	 */
	public void toSave(T param) throws MyDAOException {
		// TODO Auto-generated method stub
				Session session = null;
				Transaction tx = null;
				try{
					session = sessionFactory.openSession();
					tx= session.beginTransaction();
					session.save(param);
					tx.commit();
				}catch (HibernateException e) {
					// TODO: handle exception
					tx.rollback();
					throw new MyDAOException(e);
				}finally{
					if(session!=null &&(session.isOpen()|| session.isConnected()) ){
						session.close();
					}
				}
				
	}
	/**
	 * 
	 * @param param: Este parametro es un objeto de la clase T
	 * @return el id de la clase T 
	 * @throws MyDAOException: Cuando se genera algun problema en la consulta
	 */
	public Serializable toSaveGetId(T param) throws MyDAOException {
		// TODO Auto-generated method stub
				Session session = null;
				Transaction tx = null;
				Serializable id;
				try{
					session = sessionFactory.openSession();
					tx= session.beginTransaction();
					id=session.save(param);
					tx.commit();
				}catch (HibernateException e) {
					// TODO: handle exception
					tx.rollback();
					throw new MyDAOException(e);
				}finally{
					if(session!=null &&(session.isOpen()|| session.isConnected()) ){
						session.close();
					}
				}
				return id;
	}

	/**
	 * Metodo que modifica un objeto de la clase T
	 * @param param: Este parametro es un objeto de la clase T
	 * @throws MyDAOException: Cuando se genera algun problema en la consulta
	 */
	public void toUpdate(T param) throws MyDAOException {
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx= session.beginTransaction();
			session.update(param);
			tx.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}finally{
			if(session!=null &&(session.isOpen()|| session.isConnected()) ){
				session.close();
			}
		}
	}

	/**
	 * Metodo que elimina un objeto de tipo T
	 * @param param: Este parametro es un objeto de la clase T
	 * @throws MyDAOException: Cuando se genera algun problema en la consulta
	 */
	public void toDelete(T param) throws MyDAOException {
		
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx= session.beginTransaction();
			session.delete(param);
			tx.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}finally{
			if(session!=null &&(session.isOpen()|| session.isConnected()) ){
				session.close();
			}
		}
	}
	
	/**
	 * Metodo que guarda una lista de objetos de tipo T
	 * @param param: Este parametro es una lista de objetos de la clase T
	 * @throws MyDAOException: Cuando se genera algun problema en la consulta
	 */
	public void toSaveList(List<T> params) throws MyDAOException {
		
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx= session.beginTransaction();
			for(T param:params){
				session.save(param);
			}
			tx.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			tx.rollback();
			throw new MyDAOException(e);
		}finally{
			if(session!=null &&(session.isOpen()|| session.isConnected()) ){
				session.close();
			}
		}
	}

}

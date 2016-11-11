package co.pqrs.ing.web.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.HibernateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import co.pqrs.ing.web.dao.impl.UsuarioDAO;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.enums.Rol;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * CRUD para testear los metodos de Usuario
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config.xml"})
public class UsuarioDAOTest {
	@Autowired
	UsuarioDAO daoUser;
	
	/**
	 * método usado para testear la creacion de una lista de usuarios
	 * @throws MyDAOException
	 */
	//@Test
	public void testToList() throws MyDAOException{
		List <Usuario> usuarios;
		try {
			usuarios=daoUser.toList();
			assertTrue(usuarios.size()>0);
		} catch (HibernateException e) {
			// TODO: handle exception
			fail("Not yet implemented");
			throw new MyDAOException(e);
		}
		
	}
	
	/**
	 * método usado para testear la obtención de un Usuario
	 * @throws MyDAOException
	 */
	//@Test
	public void testToGet() throws MyDAOException{
		Usuario usuario;
		try {
			usuario=daoUser.toGet("niltonsteveen");
			assertTrue(usuario!=null);
		} catch (HibernateException e) {
			fail("Not yet implemented");
			throw new MyDAOException(e);
		}
		
	}
	
	/**
	 * método usado para testear el guardado de los Usuarios
	 * @throws MyDAOException
	 */
	//@Test
	public void testToSave() throws MyDAOException{
		Usuario usr;
		List <Usuario> usuarios;
		try {
			usr=new Usuario();
			usr.setApellidos("lopez");
			usr.setEmail("lop@udea");
			usr.setNombres("camilo");
			usr.setPassword("1234");
			usr.setUsername("camilopez");
			usr.setDireccion("cl 43");
			usr.setRol(Rol.ENCARGADO);
			usr.setHabilitado(true);
			daoUser.toSave(usr);
			usuarios=daoUser.toList();
			boolean valida=false;
			for(int i=0;i<usuarios.size();i++){
				if(usuarios.get(i).getUsername().equals(usr.getUsername())){
					valida=true;
					break;
				}else{
					valida=false;
					continue;
				}
			}
			assertTrue(valida);
		} catch (HibernateException e) {
			fail("Not yet implemented");
			throw new MyDAOException(e);
		}
		
	}
	
	/**
	 * método usado para testear la actualizacion de usuarios
	 * @throws MyDAOException
	 */
	//@Test
	public void testToUpdate() throws MyDAOException{
		List <Usuario> usuarios;
		Usuario usr;
		try {
			usuarios=daoUser.toList();
			usr=usuarios.get(0);
			usr.setApellidos("velasquez");
			boolean isUpdated;
			if(usr!=null){
				daoUser.toUpdate(usr);
				isUpdated=true;
			}else{
				isUpdated=false;
			}			
			assertTrue(isUpdated==true);
		} catch (HibernateException e) {
			// TODO: handle exception
			fail("Not yet implemented");
			throw new MyDAOException(e);
		}
		
	}
	
	/**
	 * metodo usado para testear el borrado de usuarios
	 * @throws MyDAOException
	 */
	//@Test
	public void testToDelete() throws MyDAOException{
		List <Usuario> usuarios;
		Usuario usr;
		try {
			usuarios=daoUser.toList();
			usr=usuarios.get(0);
			boolean isDeleted;
			if(usr!=null){
				daoUser.toDelete(usr);
				isDeleted=true;
			}else{
				isDeleted=false;
			}
			assertTrue(isDeleted==true);
		} catch (HibernateException e) {
			// TODO: handle exception
			fail("Not yet implemented");
			throw new MyDAOException(e);
		}
		
	}

}

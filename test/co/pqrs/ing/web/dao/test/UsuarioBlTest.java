package co.pqrs.ing.web.dao.test;

import static org.junit.Assert.*;

import java.security.MessageDigest;

import javax.management.relation.Role;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.pqrs.ing.web.dao.impl.UsuarioDAO;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.enums.Rol;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.logic.UsuarioBl;
import co.pqrs.ing.web.logic.UsuarioBlImpl;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config.xml"})
public class UsuarioBlTest {

	@Autowired
	UsuarioBl userBl;
	
	
	/**
	 * metodo usado para testear la creación de usuarios
	 */
	//@Test
	public void testCrearUsuario() {
		Usuario usuarioTest;
		Usuario loged;
		try {
			
			usuarioTest=new Usuario();
			usuarioTest.setApellidos("garcia");
			usuarioTest.setUsername("niltonsteveen");
			usuarioTest.setEmail("usuario2@hotmail.com");
			usuarioTest.setNombres("steveen");
			usuarioTest.setHabilitado(true);
			loged = new Usuario();
			loged.setRol(Rol.INVITADO);
			usuarioTest.setPassword("nilton");
			usuarioTest.setRol(Rol.CLIENTE);
			userBl.crearUsuario(usuarioTest, loged);
		} catch (MyDAOException | NullPointerException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());	
		}

	}
	
	/**
	 * metodo usado para testear la Actualización de datos de un usuario
	 */
	//@Test
	public void testActualizarUsuario() {
		Usuario usr;
		Usuario loged;
		try {
			usr=userBl.getUserById("camilo");
			usr.setEmail("usuario2@hotmail.com");
			usr.setPassword("lalalala");
			loged = new Usuario();
			loged.setRol(Rol.GERENTE);
			userBl.actualizarUsuario(usr, loged);
			
			assertTrue(true);
		} catch (MyDAOException | NullPointerException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());	
		}

	}
	
	/**
	 * método usado para testear la validación de un usuario
	 */
	//@Test
	public void testValidarUsuario() {
		
		try {
			userBl.validarUsuario("velez2", "Niltonsteveen");
			assertTrue(true);
		} catch (MyDAOException | NullPointerException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());	
		}

	}
}

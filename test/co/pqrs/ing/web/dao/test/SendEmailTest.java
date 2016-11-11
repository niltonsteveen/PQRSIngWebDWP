package co.pqrs.ing.web.dao.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import co.pqrs.ing.web.util.SendEmail;
/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 */
public class SendEmailTest {
	
	/**
	 * Metodo usado para testear el envío de email
	 */
	//@Test
	public void test() {
		
		
		try {
			SendEmail.sendMail("hcamilo.lopez@udea.edu.co", "Prueba", "Hello World!!");
			assertTrue(true);
		} catch (UnsupportedEncodingException e) {
			fail("excepcion");
		}
	}

}

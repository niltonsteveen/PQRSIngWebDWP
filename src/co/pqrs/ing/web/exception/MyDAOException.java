package co.pqrs.ing.web.exception;

import org.apache.log4j.Logger;

/**
 * Clase para Manejo de excepciones 
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que hereda de Exception y controla todas las excepciones o posibles errores encontrados
 */

public class MyDAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log =Logger.getLogger("ErrorLog");

	public MyDAOException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyDAOException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public MyDAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		log.error(arg0);
		log.error("Error",arg1);
	}

	public MyDAOException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		log.error(arg0);
	}

	public MyDAOException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		log.error(arg0);
	}
	

}

package co.pqrs.ing.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.enums.Rol;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 */
public class Utils {
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/**
	 * @param email: Correo a validar
	 * @return True: Si el correo ingresado tiene un dominio correcto
	 * False: de lo contrario
	 * @throws MyDAOException
	 */
	public static boolean validateEmail(String email) throws MyDAOException{
		try{
			Pattern pattern=Pattern.compile(PATTERN_EMAIL);
			Matcher matcher=pattern.matcher(email);
			return matcher.matches();
		}catch (Exception e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}	
	}
	
	/**
	 * @param user: Objeto de la clase Usuario
	 * @return True: Si el usuario es un gerente o es encargado, False: de lo contrario
	 * @throws MyDAOException
	 */
	public static boolean validarAdmin(Usuario user) throws MyDAOException{
		try{
				if(Rol.GERENTE.equals(user.getRol())||Rol.ENCARGADO.equals(user.getRol())){
					return true;
				}else{
					return false;
				}
		}catch (Exception e) {
			// TODO: handle exception
			throw new MyDAOException(e);
		}	
	}
	public static Rol crearRol(String rol) throws MyDAOException{
		if(rol==null){
			throw new MyDAOException("Rol no puede ser nulo");
		}else if(rol.equalsIgnoreCase(Rol.CLIENTE.name())){
			return Rol.CLIENTE;
		}else if(rol.equalsIgnoreCase(Rol.ENCARGADO.name())){
			return Rol.ENCARGADO;
		}else if(rol.equalsIgnoreCase(Rol.GERENTE.name())){
			return Rol.GERENTE;
		}else if(rol.equalsIgnoreCase(Rol.INVITADO.name())){
			return Rol.INVITADO;
		}else{
			throw new MyDAOException("El rol no existe");
		}
	}
	
	public static boolean validarRol(String rol){
		if(rol==null){
			return false;
		}else if(rol.equalsIgnoreCase(Rol.CLIENTE.name())||rol.equalsIgnoreCase(Rol.ENCARGADO.name())||
				rol.equalsIgnoreCase(Rol.GERENTE.name())||rol.equalsIgnoreCase(Rol.INVITADO.name())){
			return true;
		}else{
			return false;
		}
	}
	
}

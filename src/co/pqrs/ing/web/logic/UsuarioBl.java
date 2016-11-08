package co.pqrs.ing.web.logic;

import java.util.List;

import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Interfaz encargada de definir los metodos o acciones correspondientes a un usuario
 */
public interface UsuarioBl {
	/**
	 * @param usuario: Objeto de la clase Usuario
	 * @param loged: Objeto de la clase Usuario
	 * @throws MyDAOException
	 * Crea un usuario nuevo en la base de datos
	 */
	public void crearUsuario(Usuario usuario, Usuario loged) throws MyDAOException;
	/**
	 * @param username: Nickname del usuario (clave primaria)
	 * @param password: Contraseña del usuario
	 * @return Retorna el usuario loggueado
	 * @throws MyDAOException
	 * Autentica los datos ingresados por un usuario para su log in
	 */
	public Usuario validarUsuario(String username,String password) throws MyDAOException;
	/**
	 * @param usuario: Objeto de la clase Usuario
	 * @param loged: Objeto de la clase Usuario
	 * @throws MyDAOException
	 * Actualiza los datos personales de un usuario
	 */
	public void actualizarUsuario(Usuario usuario, Usuario loged)throws MyDAOException;
	/**
	 * @param username: Nickname del usuario (clave primaria)
	 * @return Retorna un usuario (dado su identificador)
	 * @throws MyDAOException
	 */
	public Usuario getUserById(String username)throws MyDAOException;
	
	public List<Usuario> getUsers() throws MyDAOException;
}

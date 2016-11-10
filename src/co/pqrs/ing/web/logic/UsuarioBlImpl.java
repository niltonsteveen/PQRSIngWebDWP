package co.pqrs.ing.web.logic;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.HibernateException;

import co.pqrs.ing.web.dao.impl.UsuarioDAO;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.enums.Rol;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.util.Utils;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que implementa los metodos de la interfaz UsuarioBI 
 */
public class UsuarioBlImpl implements UsuarioBl {

	
	private UsuarioDAO userDao;

	
	public UsuarioDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UsuarioDAO userDao) {
		this.userDao = userDao;
	}

	public UsuarioBlImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crearUsuario(Usuario usuario, Usuario loged) throws MyDAOException {
		if(usuario==null){
			throw new NullPointerException("Usuario no puede ser nulo");
		}
		if("".equals(usuario.getUsername())||"".equals(usuario.getApellidos())
		||"".equals(usuario.getEmail())||"".equals(usuario.getNombres())||
		"".equals(usuario.getPassword())||"".equals(usuario.getRol())){
			throw new MyDAOException("Debe especificar los datos que se marcan como obligatorios");
		}else{
			Usuario usr=userDao.toGet(usuario.getUsername());
			if(usr!=null){
				throw new MyDAOException("El usuario ya existe en la base de datos");
			}else{
				if(Utils.validateEmail(usuario.getEmail())==false){
					throw new MyDAOException("Correo invalido");
				}else if(userDao.getByEmail(usuario.getEmail())!=null){
					throw new MyDAOException("El correo ya existe, debe solicitar recuperar la contrase�a");
				}	
				else{
					String pwd=org.apache.commons.codec.digest.DigestUtils.sha256Hex(usuario.getPassword());
					if(loged.getRol().equals(Rol.INVITADO)){
						if(usuario.getRol()==null){
							usuario.setRol(Rol.CLIENTE);
						}							
						usuario.setPassword(pwd);
						if(usuario.getFechaCreacion()==null){
							usuario.setFechaCreacion(new Date());
						}
						userDao.toSave(usuario);
					}else if(loged.getRol().equals(Rol.ENCARGADO)){
						if(usuario.getRol().equals(Rol.CLIENTE)){
							usuario.setPassword(pwd);
							if(usuario.getFechaCreacion()==null){
								usuario.setFechaCreacion(new Date());
							}
							userDao.toSave(usuario);
						}else if(usuario.getRol().equals(Rol.ENCARGADO)||usuario.getRol().equals(Rol.GERENTE)
								||usuario.getRol().equals(Rol.INVITADO)){
							throw new MyDAOException("No esta permitido crear ese tipo de usuarios con esas"
									+ " credenciales");
						}
					}else if(loged.getRol().equals(Rol.GERENTE)){
						if(!usuario.getRol().equals(Rol.INVITADO)){
							usuario.setPassword(pwd);
							if(usuario.getFechaCreacion()==null){
								usuario.setFechaCreacion(new Date());
							}
							userDao.toSave(usuario);
						}else{
							throw new MyDAOException("No esta permitido crear ese tipo de usuarios con esas"
									+ " credenciales");
						}
					}else{
						throw new MyDAOException("Tienes que cerrar sesion para poder crear un nuevo usuario");
					}	
				}
			}					
		}	
		
	}

	@Override
	public Usuario validarUsuario(String username, String password) throws MyDAOException {
		Usuario user;
		if("".equals(username)||"".equals(password)){
			throw new MyDAOException("se deben ingresar las credenciales");
		}else{
			user=userDao.toGet(username);
			if(user==null){
				throw new NullPointerException("El usuario no existe");
			}else{
				if(DigestUtils.sha256Hex(password).equals(user.getPassword())){
					return user;
				}else{
					throw new MyDAOException("Usuario o Contrasena incorrectos");
				}
			}
		}

	}

	@Override
	public void actualizarUsuario(Usuario usuario,Usuario loged) throws MyDAOException{
		// TODO Auto-generated method stub
		Usuario usr;
		try {
			usr=userDao.toGet(usuario.getUsername());
			if(usr==null){
				throw new NullPointerException("Error al actualizar, el usuario no existe");
			}else{
				if("".equals(usuario.getApellidos())
				||"".equals(usuario.getEmail())||"".equals(usuario.getNombres())||
				"".equals(usuario.getPassword())||"".equals(usuario.getRol())){
					throw new MyDAOException("Debe especificar los datos que se marcan como obligatorios");
				}else{
					Usuario userByEmail=userDao.getByEmail(usuario.getEmail());
					String pwd=org.apache.commons.codec.digest.DigestUtils.sha256Hex(usuario.getPassword());
					if(Utils.validateEmail(usuario.getEmail())==false){
						throw new MyDAOException("Correo invalido");
					}else if(userByEmail!=null&&userByEmail.getUsername()!=usr.getUsername()){
						throw new MyDAOException("El correo ya existe, debe solicitar recuperar la contrase�a");
					}else if(loged.getRol().equals(Rol.INVITADO)){
						throw new MyDAOException("Debes estar autenticado para actualizar la informacion");
					}else if(loged.getRol().equals(Rol.ENCARGADO)){
							if(usuario.getRol().equals(Rol.CLIENTE)){
								usuario.setPassword(pwd);
								userDao.toUpdate(usuario);
							}else if(usuario.getRol().equals(Rol.GERENTE)
									||usuario.getRol().equals(Rol.INVITADO)){
								throw new MyDAOException("No esta permitido actualizar ese tipo de usuarios con esas"
										+ "credenciales");
							}else if(usuario.getRol().equals(Rol.ENCARGADO)&&usuario.equals(loged)){
								usuario.setPassword(pwd);
								userDao.toUpdate(usuario);
							}else{
								throw new MyDAOException("No puedes actualizar a dicho usuario");
							}
					}else if(loged.getRol().equals(Rol.GERENTE)){
						if(!usuario.getRol().equals(Rol.INVITADO)){
							usuario.setPassword(pwd);
							userDao.toUpdate(usuario);
						}else{
							throw new MyDAOException("No esta permitido actualizar ese tipo de usuarios con esas"
									+ "credenciales");
						}
					}else if(loged.getRol().equals(Rol.CLIENTE)){
						if(loged.equals(usuario)){
							usuario.setPassword(pwd);
							userDao.toUpdate(usuario);
						}else{
							throw new MyDAOException("No puedes actualizar a dicho usuario");
						}
					}
					
					
				}
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			throw new MyDAOException("Ocurrio un Error actualizando el usuario", e);
		}
	}

	@Override
	public Usuario getUserById(String username) throws MyDAOException {
		Usuario usuario;
		usuario=userDao.toGet(username);
		if(usuario==null){
			throw new MyDAOException("EL usuario no fue encontrado en base de datos");
		}
		return usuario;
	}

	@Override
	public List<Usuario> getUsers() throws MyDAOException {
		List<Usuario> users=null;
		users=userDao.toList();
		if(users==null){
			throw new MyDAOException("No hay usuarios creados en la base de datos");
		}else{
			return users;
		}
	}

}

package co.pqrs.ing.web.ws;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.relation.Role;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.enums.Rol;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.logic.UsuarioBl;
import co.pqrs.ing.web.util.Utils;
import co.pqrs.ing.web.ws.dto.UsuariosWS;

@Component
@Path("Usuario")
public class ServicioUsuarios {
	@Autowired
	UsuarioBl usuarioBl;

	public UsuarioBl getUsuarioBl() {
		return usuarioBl;
	}

	public void setUsuarioBl(UsuarioBl usuarioBl) {
		this.usuarioBl = usuarioBl;
	}

	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuariosWS> toList()throws RemoteException{
		List<UsuariosWS> result=new ArrayList<>();
		List<Usuario> datos=null;
		try {
			datos=usuarioBl.getUsers();
			for (Usuario usuario : datos) {
				UsuariosWS usuariosWS=new UsuariosWS();
				usuariosWS.setApellidos(usuario.getApellidos());
				usuariosWS.setCorreo(usuario.getEmail());
				usuariosWS.setDireccion(usuario.getDireccion());
				usuariosWS.setFechaCreacion(usuario.getFechaCreacion());
				usuariosWS.setHabilitado(usuario.getHabilitado());
				usuariosWS.setNombres(usuario.getNombres());
				usuariosWS.setPassword(usuario.getPassword());
				usuariosWS.setRol(usuario.getRol().name());
				usuariosWS.setToken(usuario.getToken());
				usuariosWS.setUsername(usuario.getUsername());
				result.add(usuariosWS);
			}
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage(),e);
		}
		return result;
	}
	
	@GET
	@Path("userById")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuariosWS UserById(@QueryParam("username")String username)throws RemoteException{
		UsuariosWS result=new UsuariosWS();
		Usuario user=null;
		try {
			user=usuarioBl.getUserById(username);
			result.setApellidos(user.getApellidos());
			result.setCorreo(user.getEmail());
			result.setDireccion(user.getDireccion());
			result.setFechaCreacion(user.getFechaCreacion());
			result.setHabilitado(user.getHabilitado());
			result.setNombres(user.getNombres());
			result.setPassword(user.getPassword());
			result.setRol(user.getRol().name());
			result.setToken(user.getToken());
			result.setUsername(user.getUsername());
			
			
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage(),e);
		}
		
		return result;
	}
	
	@GET
	@Path("createUser")
	@Produces(MediaType.TEXT_HTML)
	public String createUser(@QueryParam("usuario")UsuariosWS user,@QueryParam("loged")String logedUser)throws RemoteException{
		Usuario result=new Usuario();
		Usuario loged=null;
		try {
			loged=usuarioBl.getUserById(logedUser);
			result.setApellidos(user.getApellidos());
			result.setEmail(user.getCorreo());
			result.setDireccion(user.getDireccion());
			result.setHabilitado(user.isHabilitado());
			result.setNombres(user.getNombres());
			result.setPassword(user.getPassword());
			Rol rol=Utils.crearRol(user.getRol());
			result.setRol(rol);
			result.setToken(user.getToken());
			result.setUsername(user.getUsername());
			usuarioBl.crearUsuario(result, loged);
			return "El usuario " + result.getUsername() + " ha sido creado satisfactoriamente"; 
			
		} catch (MyDAOException e) { 
			throw new RemoteException(e.getMessage(),e);
		}
	}
	
	
	@GET
	@Path("validar")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuariosWS validate(@QueryParam("username")String username, @QueryParam("password")String pwd)throws RemoteException{
		UsuariosWS result=new UsuariosWS();
		Usuario user=null;
		try {
			user=usuarioBl.validarUsuario(username, pwd);
			result.setApellidos(user.getApellidos());
			result.setCorreo(user.getEmail());
			result.setDireccion(user.getDireccion());
			result.setFechaCreacion(user.getFechaCreacion());
			result.setHabilitado(user.getHabilitado());
			result.setNombres(user.getNombres());
			result.setPassword(user.getPassword());
			result.setRol(user.getRol().name());
			result.setToken(user.getToken());
			result.setUsername(user.getUsername());
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage(),e);
		}
		return result;
	}
	
	@GET
	@Path("actualizar")
	@Produces(MediaType.TEXT_HTML)
	public String updateUser(@QueryParam("usuario")UsuariosWS user,@QueryParam("loged")String usernameLoged )throws RemoteException, ParseException{
		Usuario result=new Usuario();
		Usuario loged=null;
		try {
			loged=usuarioBl.getUserById(usernameLoged);
			result.setApellidos(user.getApellidos());
			result.setEmail(user.getCorreo());
			result.setDireccion(user.getDireccion());
			result.setHabilitado(user.isHabilitado());
			result.setNombres(user.getNombres());
			result.setPassword(user.getPassword());
			Rol rol=Utils.crearRol(user.getRol());
			result.setRol(rol);
			result.setToken(user.getToken());
			result.setUsername(user.getUsername());
			usuarioBl.crearUsuario(result, loged);
			return "El usuario " + result.getUsername() + " ha sido actualizado satisfactoriamente"; 
			
		} catch (MyDAOException e) { 
			throw new RemoteException(e.getMessage(),e);
		}
	}
}

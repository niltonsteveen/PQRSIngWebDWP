package co.pqrs.ing.web.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.logic.UsuarioBl;
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
				usuariosWS.setRol(usuario.getRol());
				usuariosWS.setToken(usuario.getToken());
				usuariosWS.setUsername(usuario.getUsername());
				result.add(usuariosWS);
			}
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage(),e);
		}
		return result;
	}
}

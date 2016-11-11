package co.pqrs.ing.web.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.pqrs.ing.web.db.Sucursal;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.logic.SucursalBl;
import co.pqrs.ing.web.logic.UsuarioBl;
import co.pqrs.ing.web.util.Utils;
import co.pqrs.ing.web.ws.dto.SucursalWS;
/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com
 * @version 1.0.0
 * Clase que implementa los metodos de la interfaz SucursalBI 
 */
@Component
@Path("Usuario")
public class ServicioSucursal {

	@Autowired
	SucursalBl sucursalBl;
	
	@Autowired
	UsuarioBl usuarioBl;
	
	public UsuarioBl getUsuarioBl() {
		return usuarioBl;
	}

	public void setUsuarioBl(UsuarioBl usuarioBl) {
		this.usuarioBl = usuarioBl;
	}

	public SucursalBl getSucursalBl() {
		return sucursalBl;
	}

	public void setSucursalBl(SucursalBl sucursalBl) {
		this.sucursalBl = sucursalBl;
	}
	
	/**
	 * @return Retorna una lista de sucursales
	 * @throws RemoteException
	 */
	@GET
	@Path("listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SucursalWS> toList()throws RemoteException{
		List<SucursalWS> result=new ArrayList<>();
		List<Sucursal> datos=null;
		try {
			datos=sucursalBl.toList();
			for (Sucursal sucursal : datos) {
				SucursalWS sucucursalWS=new SucursalWS();
				sucucursalWS.setCodigo(sucursal.getCodigo());
				sucucursalWS.setDescripcion(sucursal.getDescripcion());
				sucucursalWS.setNombre(sucursal.getNombre());	
				result.add(sucucursalWS);
			}
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage(),e);
		}
		return result;
	}

	/**
	 * @param sucursal
	 * @param user
	 * @param pwd
	 * @return Retorna un String con el nombre de la sucursal creada
	 * @throws RemoteException
	 */
	@POST
	@Path("createSucursal")
	@Produces(MediaType.TEXT_HTML)
	public String createSucursal(@QueryParam("sucursal")SucursalWS sucursal,
			@QueryParam("usuario")String user,@QueryParam("password")String pwd)throws RemoteException{
		Sucursal result=new Sucursal();
		Usuario loged=null;
		try {
			loged=usuarioBl.getUserById(user);
			usuarioBl.validarUsuario(user, pwd);
			if(Utils.validarAdmin(loged)){
				result.setDescripcion(sucursal.getDescripcion());
				result.setNombre(sucursal.getNombre());
				sucursalBl.crearSucursal(result);
				return "La sucursal" + result.getNombre() + " ha sido creada satisfactoriamente"; 
			}else{
				return "No puedes crear sucursales con esas credenciales";
			}	
		} catch (MyDAOException e) { 
			throw new RemoteException(e.getMessage(),e);
		}
	}
	
	/**
	 * @param sucursal
	 * @param user
	 * @param pwd
	 * @return Retorna un String con el nombre de la sucursal 
	 * que fue actualizada
	 * @throws RemoteException
	 */
	@PUT
	@Path("updateSucursal")
	@Produces(MediaType.TEXT_HTML)
	public String updateSucursal(@QueryParam("sucursal")SucursalWS sucursal,
			@QueryParam("usuario")String user,@QueryParam("password")String pwd)throws RemoteException{
		Sucursal result=new Sucursal();
		Usuario loged=null;
		try {
			loged=usuarioBl.getUserById(user);
			usuarioBl.validarUsuario(user, pwd);
			if(Utils.validarAdmin(loged)){
				result.setDescripcion(sucursal.getDescripcion());
				result.setNombre(sucursal.getNombre());
				sucursalBl.actualizarSucursal(sucursal.getCodigo());
				return "La sucursal" + result.getNombre() + " ha sido actualizada satisfactoriamente"; 
			}else{
				return "No puedes actualizar sucursales con esas credenciales";
			}	
		} catch (MyDAOException e) { 
			throw new RemoteException(e.getMessage(),e);
		}
	}
}

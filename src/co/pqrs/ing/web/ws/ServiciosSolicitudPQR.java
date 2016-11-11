package co.pqrs.ing.web.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.pqrs.ing.web.db.SolicitudPQR;
import co.pqrs.ing.web.db.Sucursal;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.enums.TipoPQR;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.logic.SolicitudPQRBl;
import co.pqrs.ing.web.logic.SucursalBl;
import co.pqrs.ing.web.logic.UsuarioBl;
import co.pqrs.ing.web.util.Utils;
import co.pqrs.ing.web.ws.dto.SolicitudPQRWS;
/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com
 * @version 1.0.0
 * Clase que implementa los metodos de la interfaz SolicitudPQRBI 
 */
@Component
@Path("SolicitudPQR")
public class ServiciosSolicitudPQR {

	@Autowired
	SolicitudPQRBl pqrBl;
	
	@Autowired
	UsuarioBl usuarioBl;
	
	@Autowired
	SucursalBl sucursalBl;
	

	public SucursalBl getSucursalBl() {
		return sucursalBl;
	}

	public void setSucursalBl(SucursalBl sucursalBl) {
		this.sucursalBl = sucursalBl;
	}

	public UsuarioBl getUsuarioBl() {
		return usuarioBl;
	}

	public void setUsuarioBl(UsuarioBl usuarioBl) {
		this.usuarioBl = usuarioBl;
	}

	public SolicitudPQRBl getPqrBl() {
		return pqrBl;
	}

	public void setPqrBl(SolicitudPQRBl pqrBl) {
		this.pqrBl = pqrBl;
	}
	
	/**
	 * @param pqr
	 * @param userId
	 * @param pwd
	 * @return Retorna un String con la creación de la PQR que contiene
	 * el identificador de la solicitud
	 * @throws RemoteException
	 */
	@POST
	@Path("createPQR")
	@Produces(MediaType.TEXT_HTML)
	public String createPQR(@QueryParam("solicitud")SolicitudPQRWS pqr,@QueryParam("usuario")String userId,@QueryParam("password")String pwd)throws RemoteException{
		SolicitudPQR result=new SolicitudPQR();
		Usuario loged=null;
		Usuario userSolicitud=null;
		Sucursal sucursal=null;
		try {
			loged=usuarioBl.getUserById(userId);
			loged=usuarioBl.validarUsuario(loged.getUsername(),pwd);
			result.setDescripcion(pqr.getDescripcion());
			TipoPQR tipoPQR=Utils.crearTipo(pqr.getTipo());
			result.setTipo(tipoPQR);
			sucursal=sucursalBl.getSucursalById(pqr.getSucursalId());
			result.setSucursal(sucursal);
			userSolicitud=usuarioBl.getUserById(pqr.getUsuarioId());
			if(userSolicitud!=null){
				result.setUsuario(userSolicitud);
			}
			pqrBl.crearPQR(result, loged);
			return "La solicitud numero: " + result.getIdentificador() + " ha sido creada satisfactoriamente"; 
			
		} catch (MyDAOException e) { 
			throw new RemoteException(e.getMessage(),e);
		}
	}
	
	/**
	 * @param solicitudId
	 * @param userId
	 * @param pwd
	 * @return Retorna un String con la cancelación de una solicitud de PQR
	 * con su respectivo identificador 
	 * @throws RemoteException
	 */
	@PUT
	@Path("cancelPQR")
	@Produces(MediaType.TEXT_HTML)
	public String cancelPQR(@QueryParam("solicitud")Long solicitudId,@QueryParam("usuario")String userId,@QueryParam("password")String pwd)throws RemoteException{
		Usuario loged=null;
		SolicitudPQR solicitudPQR=null;
		try {
			loged=usuarioBl.getUserById(userId);
			loged=usuarioBl.validarUsuario(loged.getUsername(),pwd);
			solicitudPQR=pqrBl.getSolicitudById(solicitudId);
			if(solicitudPQR==null){
				throw new NullPointerException("La solicitud no existe");
			}
			pqrBl.cancelarPQR(solicitudPQR, loged);
			return "La solicitud numero: " + solicitudPQR.getIdentificador() + " ha sido cancelada satisfactoriamente"; 
			
		} catch (MyDAOException e) { 
			throw new RemoteException(e.getMessage(),e);
		}
	}
	
	/**
	 * @param user
	 * @return Retorna una lista de solicitudes de PQR
	 * @throws RemoteException
	 */
	@GET
	@Path("notificacionPQR")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SolicitudPQRWS> toList(@QueryParam("usuario")String user)throws RemoteException{
		List<SolicitudPQRWS> result=new ArrayList<>();
		List<SolicitudPQR> datos=null;
		Usuario usuario=null;
		try {
			usuario=usuarioBl.getUserById(user);
			datos=pqrBl.notificarPQR(usuario);
			for (SolicitudPQR solicitud: datos) {
				SolicitudPQRWS solicitudPQRWS=new SolicitudPQRWS();
				solicitudPQRWS.setDescripcion(solicitud.getDescripcion());
				solicitudPQRWS.setEstado(solicitud.getEstado().name());
				solicitudPQRWS.setFechaAtencion(solicitud.getFechaAtencion());
				solicitudPQRWS.setFechaCreacion(solicitud.getFechaCreacion());
				solicitudPQRWS.setFechaResolucion(solicitud.getFechaResolucion());
				solicitudPQRWS.setIdentificador(solicitud.getIdentificador());
				solicitudPQRWS.setRespuesta(solicitud.getRespuesta());
				if(solicitud.getSucursal()!=null){
					solicitudPQRWS.setSucursalId(solicitud.getSucursal().getCodigo());
				}
				solicitudPQRWS.setTipo(solicitud.getTipo().name());
				if(solicitud.getUsuarioDelegado()!=null){
					solicitudPQRWS.setUsuarioDelegadoId(solicitud.getUsuarioDelegado().getUsername());
				}
				solicitudPQRWS.setUsuarioId(solicitud.getUsuario().getUsername());
				if(solicitud.getUsuarioResuelve()!=null){
					solicitudPQRWS.setUsuarioResuelveId(solicitud.getUsuarioResuelve().getUsername());
				}
				result.add(solicitudPQRWS);
			}
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * @param solicitud
	 * @return Retorna una solicitud especifica buscada por su identificador
	 * @throws RemoteException
	 */
	@GET
	@Path("GetById")
	@Produces(MediaType.APPLICATION_JSON)
	public SolicitudPQRWS getSolicitudById(@QueryParam("solicitud")Long solicitud)throws RemoteException{
		SolicitudPQRWS result=new SolicitudPQRWS();
		SolicitudPQR pqr=null;
		try {
			pqr=pqrBl.getSolicitudById(solicitud);
			result.setUsuarioId(pqr.getUsuario().getUsername());
			result.setFechaCreacion(pqr.getFechaCreacion());
			result.setFechaAtencion(pqr.getFechaAtencion());
			result.setFechaResolucion(pqr.getFechaResolucion());
			result.setEstado(pqr.getEstado().name());
			result.setUsuarioDelegadoId(pqr.getUsuarioDelegado().getUsername());
			result.setUsuarioResuelveId(pqr.getUsuarioResuelve().getUsername());
			result.setSucursalId(pqr.getSucursal().getCodigo());
			result.setTipo(pqr.getTipo().name());
			result.setDescripcion(pqr.getDescripcion());
			result.setRespuesta(pqr.getRespuesta());
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage(),e);
		}	
		return result;
	}
	
	/**
	 * @param solicitud
	 * @param username
	 * @param user
	 * @param password
	 * @return Retorna una solicitud de PQR que fue delegada
	 * @throws RemoteException
	 */
	@PUT
	@Path("Delegar")
	@Produces(MediaType.APPLICATION_JSON)
	public SolicitudPQRWS delegarPQR(@QueryParam("solicitud")Long solicitud,
			@QueryParam("encargado")String username,
			@QueryParam("logedUser")String user,
			@QueryParam("password")String password)throws RemoteException{

		SolicitudPQRWS result=new SolicitudPQRWS();
		SolicitudPQR pqr=null;
		Usuario userEncargado=null;
		Usuario userLoged=null;
		try {
			pqr=pqrBl.getSolicitudById(solicitud);
			userEncargado=usuarioBl.getUserById(username); 
			userLoged=usuarioBl.getUserById(user);
			userLoged=usuarioBl.validarUsuario(userLoged.getUsername(),password);
			result.setUsuarioId(pqr.getUsuario().getUsername());
			result.setFechaCreacion(pqr.getFechaCreacion());
			result.setFechaAtencion(pqr.getFechaAtencion());
			result.setFechaResolucion(pqr.getFechaResolucion());
			result.setEstado(pqr.getEstado().name());
			result.setUsuarioDelegadoId(pqr.getUsuarioDelegado().getUsername());
			result.setUsuarioResuelveId(pqr.getUsuarioResuelve().getUsername());
			result.setSucursalId(pqr.getSucursal().getCodigo());
			result.setTipo(pqr.getTipo().name());
			result.setDescripcion(pqr.getDescripcion());
			result.setRespuesta(pqr.getRespuesta());
			pqrBl.delegarPQR(solicitud, userEncargado,userLoged);
			
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage(),e);
		}	
		return result;
	}
	
	/**
	 * @param solicitud
	 * @param username
	 * @param pwd
	 * @param respuesta
	 * @return Retorna un StringBuffer con un mensaje que indica que la 
	 * PQR fue respuesta 
	 * @throws RemoteException
	 */
	@PUT
	@Path("responderPQR")
	@Produces(MediaType.TEXT_HTML)
	public StringBuffer responderPQR(@QueryParam("solicitud")Long solicitud,
			@QueryParam("encargado")String username,
			@QueryParam("password")String pwd,
			@QueryParam("respuesta")String respuesta)throws RemoteException{
		Usuario userEncargado=null;
		StringBuffer result=new StringBuffer();
		try {
			result.append("Se ha generado una respuesta a la solicitud Numero:");
			result.append(String.valueOf(solicitud));
			result.append("\nrespuesta:\n");
			result.append(respuesta);			
			pqrBl.responderPQR(solicitud, userEncargado, respuesta);
			
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage(),e);
		}	
		return result;
	}
	

}

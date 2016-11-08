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

import co.pqrs.ing.web.db.SolicitudPQR;
import co.pqrs.ing.web.db.Sucursal;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.enums.EstadoPQR;
import co.pqrs.ing.web.enums.Rol;
import co.pqrs.ing.web.enums.TipoPQR;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.logic.SolicitudPQRBl;
import co.pqrs.ing.web.logic.SucursalBl;
import co.pqrs.ing.web.logic.UsuarioBl;
import co.pqrs.ing.web.util.Utils;
import co.pqrs.ing.web.ws.dto.SolicitudPQRWS;
import co.pqrs.ing.web.ws.dto.SolicitudPQRWS;

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
	
	@GET
	@Path("createPQR")
	@Produces(MediaType.TEXT_HTML)
	public String createPQR(@QueryParam("solicitud")SolicitudPQRWS pqr,@QueryParam("usuario")String userId,@QueryParam("password")String pwd)throws RemoteException{
		SolicitudPQR result=new SolicitudPQR();
		Usuario loged=null;
		Usuario userSolicitud=null;
		try {
			loged=usuarioBl.getUserById(userId);
			loged=usuarioBl.validarUsuario(loged.getUsername(),pwd);
			result.setDescripcion(pqr.getDescripcion());
			TipoPQR tipoPQR=Utils.crearTipo(pqr.getTipo());
			result.setTipo(tipoPQR);
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
	
	@GET
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
	
}

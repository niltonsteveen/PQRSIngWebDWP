package co.pqrs.ing.web.logic;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import co.pqrs.ing.web.dao.impl.SolicitudPQRDAO;
import co.pqrs.ing.web.dao.impl.UsuarioDAO;
import co.pqrs.ing.web.db.SolicitudPQR;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.enums.EstadoPQR;
import co.pqrs.ing.web.enums.Rol;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.util.SendEmail;
import co.pqrs.ing.web.util.Utils;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que implementa los metodos de la interfaz SolicitudPQRBI
 */
public class SolicitudPQRBlImpl implements SolicitudPQRBl {

	SolicitudPQRDAO pqrdao;
	UsuarioDAO usuarioDAO;
	
	EncuestaSatisfaccionBI encuestaSatisfaccionBl;
	PlantillaEncuestaBI plantillaEncuestaBl;
	

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public SolicitudPQRDAO getPqrdao() {
		return pqrdao;
	}

	public void setPqrdao(SolicitudPQRDAO pqrdao) {
		this.pqrdao = pqrdao;
	}

	
	
	public PlantillaEncuestaBI getPlantillaEncuestaBl() {
		return plantillaEncuestaBl;
	}

	public void setPlantillaEncuestaBl(PlantillaEncuestaBI plantillaEncuestaBl) {
		this.plantillaEncuestaBl = plantillaEncuestaBl;
	}

	public EncuestaSatisfaccionBI getEncuestaSatisfaccionBl() {
		return encuestaSatisfaccionBl;
	}

	public void setEncuestaSatisfaccionBl(EncuestaSatisfaccionBI encuestaSatisfaccionBl) {
		this.encuestaSatisfaccionBl = encuestaSatisfaccionBl;
	}

	public SolicitudPQRBlImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crearPQR(SolicitudPQR solicitudPQR, Usuario usuario) throws MyDAOException {
		Usuario usrSolicitud;
		if(usuario.getRol().equals(Rol.GERENTE)||usuario.getRol().equals(Rol.INVITADO)){
			throw new MyDAOException("Para realizar una solicitud debes estar registrado como usuario"
					+ " o encargado");
		}
		if(solicitudPQR==null){
			throw new MyDAOException("No has ingresado alguna solicitud");
		}
		if(solicitudPQR.getUsuario()==null){
			throw new MyDAOException("La solicitud tiene que tener un cliente asociado");
		}
		else if(solicitudPQR.getFechaCreacion()==null||solicitudPQR.getEstado()==null||
				   solicitudPQR.getTipo()==null||"".equals(solicitudPQR.getDescripcion())){
					throw new MyDAOException("Se deben ingresar los datos que se marcan como obligatorios");
		}else{
			usrSolicitud=usuarioDAO.toGet(solicitudPQR.getUsuario().getUsername());
			if(usrSolicitud==null){
				throw new MyDAOException("El usuario de la solicitud no existe en la base de datos");
			}
			else{
				if(usrSolicitud.getHabilitado()==false){
					throw new MyDAOException("El usuario que crea la solicitud tiene que estar habilitado en el sistema");
				}else{
					pqrdao.toSave(solicitudPQR);
				}
			}
		}
	}

	@Override
	public void cancelarPQR(SolicitudPQR solicitudPQR, Usuario usuario) throws MyDAOException {
		// TODO Auto-generated method stub
		if(usuario.getRol().equals(Rol.INVITADO)){
			throw new MyDAOException("Para cancelar una solicitud debe estar autenticado con sus "
					+ "credenciales");
		}if(solicitudPQR==null){
			throw new MyDAOException("Debes seleccionar una solicitud para cancelar");	
		}else if(usuario.getRol().equals(Rol.CLIENTE)&&solicitudPQR.getEstado()!=EstadoPQR.SIN_ATENDER){
			if(solicitudPQR.getEstado().equals(EstadoPQR.EN_CURSO)){
				throw new MyDAOException("No puedes cancelar una solicitud que esta en curso");
			}else if(solicitudPQR.getEstado().equals(EstadoPQR.RESUELTA_NEGATIVAMENTE)||
					solicitudPQR.getEstado().equals(EstadoPQR.RESUELTA_NEGATIVAMENTE)){
				throw new MyDAOException("No se puede cancelar una solicitud que ya esta resuelta");
			}
		}else{
			pqrdao.toDelete(solicitudPQR);
		}
		
	}

	@Override
	public List<SolicitudPQR> notificarPQR(Usuario usuario) throws MyDAOException {
		// TODO Auto-generated method stub
		List<SolicitudPQR> pqrs;
		if(!usuario.getRol().equals(Rol.CLIENTE)&&!usuario.getRol().equals(Rol.INVITADO)){
			pqrs=pqrdao.toListByState();
			for(int i=0;i<pqrs.size();i++){
				pqrdao.toUpdate(pqrs.get(i));
			}
			return pqrs;
		}else{
			throw new MyDAOException("Permiso denegado");
		}
	}

	@Override
	public SolicitudPQR getSolicitudById(Long id) throws MyDAOException {
		SolicitudPQR pqr=pqrdao.toGet(id);
		return pqr;
	}

	@Override
	public void delegarPQR(Long solicitudId, Usuario encargado, Usuario loged) throws MyDAOException {
		// TODO Auto-generated method stub
		if(solicitudId==null){
			throw new MyDAOException("la solicitud no puede ser nula");
		}
		if(encargado==null){
			throw new MyDAOException("El encargado no existe", new NullPointerException());
		}
		if(loged.getRol().equals(Rol.GERENTE)){
			SolicitudPQR solicitudPqr= pqrdao.toGet(solicitudId);
			solicitudPqr.setUsuarioDelegado(encargado);
			solicitudPqr.setEstado(EstadoPQR.EN_CURSO);
			pqrdao.toUpdate(solicitudPqr);
		}else{
			throw new MyDAOException("No cuenta con los permisos necesarios");
		}
	}
	
	
	@Override
	public void responderPQR(Long solicitudId, Usuario encargado, String respuesta) throws MyDAOException {
		// TODO Auto-generated method stub
		if(solicitudId==null){
			throw new MyDAOException("la solicitud no puede ser nula");
		}
		if(Utils.validarAdmin(encargado)){
			SolicitudPQR solicitudPqr= pqrdao.toGet(solicitudId);
			solicitudPqr.setUsuarioResuelve(encargado);
			solicitudPqr.setFechaResolucion(new Date());
			solicitudPqr.setRespuesta(respuesta);
			solicitudPqr.setEstado(EstadoPQR.RESUELTA_POSITIVAMENTE);
			pqrdao.toUpdate(solicitudPqr);
			
			StringBuilder bodyEmail = new StringBuilder();
			bodyEmail.append("Senor(a): \n");
			bodyEmail.append(solicitudPqr.getUsuario().getNombres());
			bodyEmail.append(" ");
			bodyEmail.append(solicitudPqr.getUsuario().getApellidos());
			bodyEmail.append("La solicitud realizada por usted ha sido respondida: \n");
			bodyEmail.append(respuesta);
			bodyEmail.append("si desea ver mas detalles puede iniciar sesion en el sistema. ");
		
			try {
				SendEmail.sendMail(solicitudPqr.getUsuario().getEmail(), "Respuesta solicitud numero "+ solicitudPqr.getIdentificador(), bodyEmail.toString());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new MyDAOException("Ha ocurrido un error enviando respuesta de solicitud"+ solicitudPqr.getIdentificador() , e);
			
			
			}
			/*EncuestaSatisfaccion encuesta= new EncuestaSatisfaccion();
			PlantillaEncuesta plantilla = plantillaEncuestaBl.obtenerPorTipo(solicitudPqr.getTipo());
			if(plantilla==null){
				throw new MyDAOException("no existe plantilla para este tipo de PQR");
			}
			encuesta.setPlantilla(plantilla);
			encuesta.setSolicitud(solicitudPqr);
			encuesta.setFechaCreacion(new Date());
			Long idEncuesta = encuestaSatisfaccionBl.guardarEncuesta(encuesta);
			encuesta = encuestaSatisfaccionBl.cargarEncuesta(idEncuesta);
			encuestaSatisfaccionBl.enviarEncuestaSatisfaccion(encuesta);*/
			
	}else{
		throw new MyDAOException("No cuenta con los permisos necesarios");
	}
	
	}


}

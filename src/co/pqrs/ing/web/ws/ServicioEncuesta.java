package co.pqrs.ing.web.ws;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.pqrs.ing.web.db.EncuestaSatisfaccion;
import co.pqrs.ing.web.db.PlantillaEncuesta;
import co.pqrs.ing.web.db.Pregunta;
import co.pqrs.ing.web.db.Respuesta;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.logic.EncuestaSatisfaccionBI;
import co.pqrs.ing.web.logic.PlantillaEncuestaBI;
import co.pqrs.ing.web.logic.PreguntaBI;
import co.pqrs.ing.web.logic.RespuestasBI;
import co.pqrs.ing.web.logic.UsuarioBl;
import co.pqrs.ing.web.util.Utilities;
import co.pqrs.ing.web.util.Utils;
import co.pqrs.ing.web.ws.dto.EncuestaWS;
import co.pqrs.ing.web.ws.dto.PlantillaEncuestaWS;
import co.pqrs.ing.web.ws.dto.PreguntaWS;
import co.pqrs.ing.web.ws.dto.RespuestaWS;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com
 * @version 1.0.0
 * Clase que implementa los metodos de la interfaz EncuestaSatisfaccionBI 
 */
@Component
@Path("Encuesta")
public class ServicioEncuesta {

	public UsuarioBl getUsuarioBl() {
		return usuarioBl;
	}

	public void setUsuarioBl(UsuarioBl usuarioBl) {
		this.usuarioBl = usuarioBl;
	}

	public PlantillaEncuestaBI getPlantillaBl() {
		return plantillaBl;
	}

	public void setPlantillaBl(PlantillaEncuestaBI plantillaBl) {
		this.plantillaBl = plantillaBl;
	}

	public PreguntaBI getPreguntaBl() {
		return preguntaBl;
	}

	public void setPreguntaBl(PreguntaBI preguntaBl) {
		this.preguntaBl = preguntaBl;
	}

	public RespuestasBI getRespuestaBl() {
		return respuestaBl;
	}

	public void setRespuestaBl(RespuestasBI respuestaBl) {
		this.respuestaBl = respuestaBl;
	}

	public EncuestaSatisfaccionBI getEncuestaBl() {
		return encuestaBl;
	}

	public void setEncuestaBl(EncuestaSatisfaccionBI encuestaBl) {
		this.encuestaBl = encuestaBl;
	}

	@Autowired
	UsuarioBl usuarioBl;
	@Autowired
	PlantillaEncuestaBI plantillaBl;
	@Autowired
	PreguntaBI preguntaBl;
	@Autowired
	RespuestasBI respuestaBl;
	@Autowired
	EncuestaSatisfaccionBI encuestaBl;
	
	
	/**
	 * @param plantilla
	 * @param user
	 * @param pass
	 * @throws RemoteException
	 * Crea una plantilla base
	 */
	
	@POST
	@Path("crearPlantilla")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearPlantilla(@QueryParam("plantilla")PlantillaEncuestaWS plantilla,@QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{

		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				PlantillaEncuesta plantillaBd = Utilities.convertirPlantilla(plantilla);	
				plantillaBl.crearPlantillaEncuesta(plantillaBd);
				return Response.status(201).entity("Plantilla creada exitosamente").build();
			}else{
				throw new RemoteException("Este usuario no tiene permitido modificar plantillas");
			}
		
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
	/**
	 * @param plantilla
	 * @param user
	 * @param pass
	 * @throws RemoteException
	 * Modifica una plantilla
	 */

	@PUT
	@Path("modificarPlantilla")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ModificarPlantilla(@QueryParam("plantilla")PlantillaEncuestaWS plantilla,@QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{

		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				PlantillaEncuesta plantillaBd = Utilities.convertirPlantilla(plantilla);
				plantillaBl.modificarPlantillaEncuesta(plantillaBd);
				return Response.status(201).entity("Plantilla modificada exitosamente").build();
			}else{
				throw new RemoteException("Este usuario no tiene permitido modificar plantillas");
			}
		
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
	/**
	 * @param plantilla
	 * @param user
	 * @param pass
	 * @return Se retorna una Lista plantillas para una Encuesta
	 * @throws RemoteException
	 */
	@GET
	@Path("listarPlantillas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlantillaEncuestaWS> listarPlantillas(@QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				List<PlantillaEncuestaWS> plantillas= new LinkedList<>();
				List<PlantillaEncuesta> plantillasBd = plantillaBl.listarPlantillas();
				for(PlantillaEncuesta plantillaBd: plantillasBd){
					PlantillaEncuestaWS plantillaws = Utilities.convertirPlantilla(plantillaBd);
					plantillas.add(plantillaws);
					
				}
				return plantillas;
			}else{
				throw new RemoteException("Este usuario no tiene permitido listar plantillas");
			}
		
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param pregunta
	 * @param user
	 * @param pass
	 * @throws RemoteException
	 * Guarda una pregunta en una plantilla para una Encuesta
	 */
	@POST
	@Path("guardarPregunta")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response guardarPregunta(@QueryParam("pregunta")PreguntaWS pregunta, @QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				if(pregunta!=null){
					throw new MyDAOException("La pregunta no puede ser nula");
				}
				Pregunta preguntabd = Utilities.convertirpregunta(pregunta);
				preguntaBl.agregarPregunta(preguntabd);
				return Response.status(201).entity("Pregunta creada exitosamente").build();
			}else{
				throw new RemoteException("Este usuario no tiene permitido modificar preguntas");
			}
		
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
	/**
	 * @param pregunta
	 * @param user
	 * @param pass
	 * @throws RemoteException
	 * Modifica una pregunta en una plantilla
	 */
	@PUT
	@Path("modificarPregunta")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificarPregunta(@QueryParam("pregunta")PreguntaWS pregunta, @QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				if(pregunta!=null){
					throw new MyDAOException("La plantilla no puede ser nula");
				}
				Pregunta preguntabd = Utilities.convertirpregunta(pregunta);
				preguntaBl.modificarPregunta(preguntabd);
				return Response.status(201).entity("Plantilla creada exitosamente").build();
			}else{
				throw new RemoteException("Este usuario no tiene permitido modificar preguntas");
			}
		
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
	/**
	 * @param codigoPlantilla
	 * @param user
	 * @param pass
	 * @return Retorna una lista de preguntas para una plantilla
	 * @throws RemoteException
	 */
	@GET
	@Path("listarPreguntasPorPlantilla")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PreguntaWS> listarPreguntasPorPlantilla(@QueryParam("codigo")Long codigoPlantilla, @QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				List<PreguntaWS> preguntas= new LinkedList<>();
				PlantillaEncuesta plantilla = new PlantillaEncuesta();
				plantilla.setCodigo(codigoPlantilla);
				List<Pregunta> preguntasBd = preguntaBl.listarPreguntasByPlantilla(plantilla);
				for(Pregunta preguntaBd: preguntasBd){
					PreguntaWS preguntaWs = Utilities.convertirpregunta(preguntaBd);
					preguntas.add(preguntaWs);
					
				}
				return preguntas;
			}else{
				throw new RemoteException("Este usuario no tiene permitido listar preguntas");
			}
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
   
	/**
	 * @param codigoEncuesta
	 * @param user
	 * @param pass
	 * @return Retorna una encuesta generada a partir de una plantilla
	 * con preguntas
	 * @throws RemoteException
	 */
	@GET
	@Path("presentarEncuestaCliente")
	@Produces(MediaType.APPLICATION_JSON)
	public EncuestaWS presentarEncuesta(@QueryParam("codigo")Long codigoEncuesta, @QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(loged!=null){
				EncuestaSatisfaccion encuesta= encuestaBl.cargarEncuesta(codigoEncuesta);
				if(encuesta!=null){
					EncuestaWS encuestaWs = new EncuestaWS();
					List<Pregunta> preguntas = preguntaBl.listarPreguntasByPlantilla(encuesta.getPlantilla());
					List<PreguntaWS> preguntasWs = new LinkedList<>();
					for(Pregunta p: preguntas){
						PreguntaWS preguntaWs = Utilities.convertirpregunta(p);
						preguntasWs.add(preguntaWs);
					}
					encuestaWs.setPreguntas(preguntasWs);
					return encuestaWs;
				}else{
					throw new MyDAOException("La encuesta solicitada no existe");
				}
				
			}else{
				throw new RemoteException("Este usuario no tiene permitido responder esta encuesta ");
			}
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
	/**
	 * @param respuestas
	 * @param codigoEncuesta
	 * @param user
	 * @param pass
	 * @throws RemoteException
	 * Guarda las respuestas a una encuesta
	 */

	@POST
	@Path("responderEncuesta")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response responderEncuesta(@QueryParam("codigo")List<RespuestaWS> respuestas, @QueryParam("codigoEncuesta")Long codigoEncuesta, @QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(loged!=null){
				if(respuestas!=null&&respuestas.size()>0){
					EncuestaSatisfaccion encuesta= encuestaBl.cargarEncuesta(codigoEncuesta);
					if(encuesta!=null){
						List<Respuesta> respuestasBd= new LinkedList<>();
						for(RespuestaWS r: respuestas){
							Respuesta resp= Utilities.convertirRespuesta(r, codigoEncuesta);
							respuestasBd.add(resp);
						}
						respuestaBl.guardarRespuestas(respuestasBd);
						return Response.status(201).entity("Respuestas guardadas exitosamente").build();
					}else{
						throw new MyDAOException("la encuesta que intenta responder no existe");
					}				
				}else{
					throw new MyDAOException("Debe consignar al menos una respuesta");
				}
				
			}else{
				throw new RemoteException("Este usuario no tiene permitido responder esta encuesta ");
			}
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
	/**
	 * @param codigoEncuesta
	 * @param user
	 * @param pass
	 * @return valida que el usuario logueado sea el administrador y luego
	 * retorna una encuesta generada con sus respectivas respuestas
	 * @throws RemoteException
	 */
	@GET
	@Path("presentarEncuestaAdministrador")
	@Produces(MediaType.APPLICATION_JSON)
	public EncuestaWS presentarEncuestaAdministrador(@QueryParam("codigo")Long codigoEncuesta, @QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				EncuestaSatisfaccion encuesta= encuestaBl.cargarEncuesta(codigoEncuesta);
				if(encuesta!=null){
					EncuestaWS encuestaWs = new EncuestaWS();
					List<Pregunta> preguntas = preguntaBl.listarPreguntasByPlantilla(encuesta.getPlantilla());
					List<PreguntaWS> preguntasWs = new LinkedList<>();
					for(Pregunta p: preguntas){
						PreguntaWS preguntaWs = Utilities.convertirpregunta(p);
						preguntasWs.add(preguntaWs);
					}
					encuestaWs.setPreguntas(preguntasWs);
					List<Respuesta> respuestasBd = respuestaBl.listarRespuestasByEncuesta(encuesta);
					List<RespuestaWS> respuestasWs = new LinkedList<>();
					for(Respuesta resp: respuestasBd){
						RespuestaWS respuestaWs = Utilities.convertirRespuesta(resp);
						respuestasWs.add(respuestaWs);
					}
			
					return encuestaWs;
				}else{
					throw new MyDAOException("La encuesta solicitada no existe");
				}
				
			}else{
				throw new RemoteException("Este usuario no tiene permitido responder ver encuesta ");
			}
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
	/**
	 * @param solicitudId
	 * @param user
	 * @param pass
	 * @return valida que el usuario logueado sea el administrador y luego
	 * retorna una encuesta generada con sus respectivas respuestas 
	 * por solicitud
	 * @throws RemoteException
	 */
	@GET
	@Path("presentarEncuestaAdministradorBySolicitud")
	@Produces(MediaType.APPLICATION_JSON)
	public EncuestaWS presentarEncuestaAdministradorBySolicitud(@QueryParam("solicitudId")Long solicitudId, @QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				EncuestaSatisfaccion encuesta= encuestaBl.cargarEncuestaBySolicitud(solicitudId);
				if(encuesta!=null){
					EncuestaWS encuestaWs = new EncuestaWS();
					List<Pregunta> preguntas = preguntaBl.listarPreguntasByPlantilla(encuesta.getPlantilla());
					List<PreguntaWS> preguntasWs = new LinkedList<>();
					for(Pregunta p: preguntas){
						PreguntaWS preguntaWs = Utilities.convertirpregunta(p);
						preguntasWs.add(preguntaWs);
					}
					encuestaWs.setPreguntas(preguntasWs);
					List<Respuesta> respuestasBd = respuestaBl.listarRespuestasByEncuesta(encuesta);
					List<RespuestaWS> respuestasWs = new LinkedList<>();
					for(Respuesta resp: respuestasBd){
						RespuestaWS respuestaWs = Utilities.convertirRespuesta(resp);
						respuestasWs.add(respuestaWs);
					}
			
					return encuestaWs;
				}else{
					throw new MyDAOException("La encuesta solicitada no existe");
				}
				
			}else{
				throw new RemoteException("Este usuario no tiene permitido ver esta encuesta ");
			}
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
}

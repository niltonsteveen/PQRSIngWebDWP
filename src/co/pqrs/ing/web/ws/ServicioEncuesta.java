package co.pqrs.ing.web.ws;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import co.pqrs.ing.web.db.PlantillaEncuesta;
import co.pqrs.ing.web.db.Pregunta;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.logic.EncuestaSatisfaccionBI;
import co.pqrs.ing.web.logic.PlantillaEncuestaBI;
import co.pqrs.ing.web.logic.PreguntaBI;
import co.pqrs.ing.web.logic.RespuestasBI;
import co.pqrs.ing.web.logic.UsuarioBl;
import co.pqrs.ing.web.util.Utilities;
import co.pqrs.ing.web.util.Utils;
import co.pqrs.ing.web.ws.dto.PlantillaEncuestaWS;
import co.pqrs.ing.web.ws.dto.PreguntaWS;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com
 * @version 1.0.0
 * Clase que implementa los metodos de la interfaz EncuestaSatisfaccionBI 
 */
@Path("Encuesta")
public class ServicioEncuesta {

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
	
	
	
	@GET
	@Path("CrearPlantilla")
	public void crearPlantilla(@QueryParam("plantilla")PlantillaEncuestaWS plantilla,@QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				PlantillaEncuesta plantillaBd = Utilities.convertirPlantilla(plantilla);
				plantillaBl.crearPlantillaEncuesta(plantillaBd);
			}else{
				throw new RemoteException("Este usuario no tiene permitido modificar plantillas");
			}
		
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
	@GET
	@Path("ModificarPlantilla")
	public void ModificarPlantilla(@QueryParam("plantilla")PlantillaEncuestaWS plantilla,@QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				PlantillaEncuesta plantillaBd = Utilities.convertirPlantilla(plantilla);
				plantillaBl.modificarPlantillaEncuesta(plantillaBd);
			}else{
				throw new RemoteException("Este usuario no tiene permitido modificar plantillas");
			}
		
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
	
	@GET
	@Path("listarPlantillas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlantillaEncuestaWS> listarPlantillas(@QueryParam("plantilla")PlantillaEncuestaWS plantilla,@QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
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
	
	
	@GET
	@Path("guardarPregunta")
	public void guardarPregunta(@QueryParam("pregunta")PreguntaWS pregunta, @QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				if(pregunta!=null){
					throw new MyDAOException("La plantilla no puede ser nula");
				}
				Pregunta preguntabd = Utilities.convertirpregunta(pregunta);
				preguntaBl.agregarPregunta(preguntabd);
			}else{
				throw new RemoteException("Este usuario no tiene permitido modificar preguntas");
			}
		
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
	@GET
	@Path("modificarPregunta")
	public void modificarPregunta(@QueryParam("pregunta")PreguntaWS pregunta, @QueryParam("user")String user, @QueryParam("pass")String pass) throws RemoteException{
		try {
			Usuario loged = usuarioBl.validarUsuario(user, pass);
			if(Utils.validarAdmin(loged)){
				if(pregunta!=null){
					throw new MyDAOException("La plantilla no puede ser nula");
				}
				Pregunta preguntabd = Utilities.convertirpregunta(pregunta);
				preguntaBl.modificarPregunta(preguntabd);
			}else{
				throw new RemoteException("Este usuario no tiene permitido modificar preguntas");
			}
		
		} catch (MyDAOException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	
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
   
	
	
	
}

package co.pqrs.ing.web.util;

import java.util.Date;

import co.pqrs.ing.web.db.EncuestaSatisfaccion;
import co.pqrs.ing.web.db.PlantillaEncuesta;
import co.pqrs.ing.web.db.Pregunta;
import co.pqrs.ing.web.db.Respuesta;
import co.pqrs.ing.web.enums.TipoPQR;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.ws.dto.EncuestaWS;
import co.pqrs.ing.web.ws.dto.PlantillaEncuestaWS;
import co.pqrs.ing.web.ws.dto.PreguntaWS;
import co.pqrs.ing.web.ws.dto.RespuestaWS;
import co.pqrs.ing.web.ws.dto.SolicitudPQRWS;

public class Utilities {

	public static PlantillaEncuesta convertirPlantilla(PlantillaEncuestaWS plantilla) throws MyDAOException{	
		if(plantilla==null){
			throw new MyDAOException("La plantilla no puede ser nula");
		}
		PlantillaEncuesta plant= new PlantillaEncuesta();
		plant.setCodigo(plantilla.getCodigo());
		TipoPQR tipo = crearTipoPQR(plantilla.getTipo());
		plant.setTipo(tipo);
		plant.setFechaCreacion(new Date());
		plant.setNombre(plantilla.getNombre());
		return plant;	
	}
	
	public static PlantillaEncuestaWS convertirPlantilla(PlantillaEncuesta plantilla) throws MyDAOException{	
		if(plantilla==null){
			throw new MyDAOException("La plantilla no puede ser nula");
		}
		PlantillaEncuestaWS plant= new PlantillaEncuestaWS();
		plant.setCodigo(plantilla.getCodigo());
		plant.setTipo(plantilla.getTipo().name());
		plant.setFechaCreacion(new Date());
		plant.setNombre(plantilla.getNombre());
		return plant;	
	}
	

	public static Pregunta convertirpregunta(PreguntaWS pregunta) throws MyDAOException{	
		if(pregunta==null){
			throw new MyDAOException("La pregunta no puede ser nula");
		}
		Pregunta preg= new Pregunta();
		preg.setCodigo(pregunta.getCodigo());
		preg.setHabilitada(preg.getHabilitada());
		preg.setObligatoria(pregunta.getObligatoria());
		preg.setPregunta(pregunta.getPregunta());
		PlantillaEncuesta p = new PlantillaEncuesta();
		p.setCodigo(preg.getPlantilla().getCodigo());
		preg.setPlantilla(p);
		return preg;	
	}
	
	public static PreguntaWS convertirpregunta(Pregunta pregunta) throws MyDAOException{	
		if(pregunta==null){
			throw new MyDAOException("La pregunta no puede ser nula");
		}
		PreguntaWS preg= new PreguntaWS();
		preg.setCodigo(pregunta.getCodigo());
		preg.setHabilitada(preg.getHabilitada());
		preg.setObligatoria(pregunta.getObligatoria());
		preg.setPregunta(pregunta.getPregunta());
		PlantillaEncuestaWS p = new PlantillaEncuestaWS();
		p.setCodigo(pregunta.getPlantilla().getCodigo());
		preg.setPlantilla(p);
		return preg;	
	}
	
	
	public static EncuestaWS convertirEncuesta(EncuestaSatisfaccion encuesta) throws MyDAOException{	
		if(encuesta==null){
			throw new MyDAOException("La pregunta no puede ser nula");
		}
		EncuestaWS encuestaWs= new EncuestaWS();
		encuestaWs.setCodigo(encuesta.getCodigo());
		encuestaWs.setFechaCreacion(encuesta.getFechaCreacion());
		PlantillaEncuestaWS plantilla = convertirPlantilla(encuesta.getPlantilla());
		encuestaWs.setPlantilla(plantilla);
		SolicitudPQRWS solicitud = new SolicitudPQRWS();
		solicitud.setIdentificador(encuesta.getSolicitud().getIdentificador());
		encuestaWs.setSolicitudId(solicitud);
		return encuestaWs;	
	}
	
	public static Respuesta convertirRespuesta(RespuestaWS respuesta, Long encuestaId) throws MyDAOException{
		if(respuesta==null){
			throw new MyDAOException("La respuesta no puede ser nula");
		}
		
		Respuesta respuestaBd= new Respuesta();
		EncuestaSatisfaccion encuesta= new EncuestaSatisfaccion();
		encuesta.setCodigo(encuestaId);
		respuestaBd.setEncuesta(encuesta);
		Pregunta pregunta = new Pregunta();
		pregunta.setCodigo(respuesta.getPregunta().getCodigo());
		respuestaBd.setPregunta(pregunta);
		respuestaBd.setRespuesta(respuesta.getRespuesta());
		return respuestaBd;	
		
	}
	
	public static RespuestaWS convertirRespuesta(Respuesta respuesta) throws MyDAOException{
		if(respuesta==null){
			throw new MyDAOException("La respuesta no puede ser nula");
		}
		RespuestaWS respuestaWs= new RespuestaWS();
		respuestaWs.setCodigo(respuesta.getCodigo());
		respuestaWs.setEncuestaId(convertirEncuesta(respuesta.getEncuesta()));
		respuestaWs.setPregunta(convertirpregunta(respuesta.getPregunta()));
		respuestaWs.setRespuesta(respuesta.getRespuesta());
		return respuestaWs;	
		
	}
	
	
	public static TipoPQR crearTipoPQR(String tipo) throws MyDAOException{
		if(tipo==null){
			throw new MyDAOException("El tipo no puede ser nulo");
		}
		if(tipo.equalsIgnoreCase(TipoPQR.PETICION.name())){
			return  TipoPQR.PETICION;
		}else if(tipo.equalsIgnoreCase(TipoPQR.QUEJA.name())){
			return  TipoPQR.QUEJA;
		}else if(tipo.equalsIgnoreCase(TipoPQR.RECLAMO.name())){
			return  TipoPQR.RECLAMO;
		}else if(tipo.equalsIgnoreCase(TipoPQR.SUGERENCIA.name())){
			return  TipoPQR.SUGERENCIA;
		}else{
			throw new MyDAOException("tipo de PQR no valido");
		}
	}
	
	
}

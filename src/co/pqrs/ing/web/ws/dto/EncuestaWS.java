package co.pqrs.ing.web.ws.dto;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

public class EncuestaWS {

	
	private Long codigo;
	private SolicitudPQRWS solicitudId;
	private PlantillaEncuestaWS plantilla;
	private Date fechaCreacion;
	private List<PreguntaWS> preguntas;
	private List<RespuestaWS> respuestas;
	
	public static EncuestaWS fromString(String jsonRepresentation){
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
		EncuestaWS encuesta=null;
		try {
			encuesta=mapper.readValue(jsonRepresentation, EncuestaWS.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return encuesta;
	}
	
	
	public EncuestaWS() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EncuestaWS(Long codigo, SolicitudPQRWS solicitudId, PlantillaEncuestaWS plantilla, Date fechaCreacion,
			List<PreguntaWS> preguntas, List<RespuestaWS> respuestas) {
		super();
		this.codigo = codigo;
		this.solicitudId = solicitudId;
		this.plantilla = plantilla;
		this.fechaCreacion = fechaCreacion;
		this.preguntas = preguntas;
		this.respuestas = respuestas;
	}


	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public SolicitudPQRWS getSolicitudId() {
		return solicitudId;
	}
	public void setSolicitudId(SolicitudPQRWS solicitudId) {
		this.solicitudId = solicitudId;
	}
	public PlantillaEncuestaWS getPlantilla() {
		return plantilla;
	}
	public void setPlantilla(PlantillaEncuestaWS plantilla) {
		this.plantilla = plantilla;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public List<PreguntaWS> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<PreguntaWS> preguntas) {
		this.preguntas = preguntas;
	}
	public List<RespuestaWS> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(List<RespuestaWS> respuestas) {
		this.respuestas = respuestas;
	}
	
	
	
	
	
}

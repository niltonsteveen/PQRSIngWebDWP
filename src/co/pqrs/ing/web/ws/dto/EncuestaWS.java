package co.pqrs.ing.web.ws.dto;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

@XmlRootElement
public class EncuestaWS {

	
	private Long codigo;
	private SolicitudPQRWS solicitudId;
	private PlantillaEncuestaWS plantilla;
	private Date fechaCreacion;
	private List<PreguntaWS> preguntas;
	private List<RespuestaWS> respuestas;
	
	/**
	 * @param jsonRepresentation
	 * @return Retorna un objeto de tipo EncuestaWS a partir de una
	 * cadena en formato json capturada por url
	 */
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
	}

	/**
	 * @param codigo
	 * @param solicitudId
	 * @param plantilla
	 * @param fechaCreacion
	 * @param preguntas
	 * @param respuestas
	 * Método constructor de la clase EncuestaWS
	 */
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

	/**
	 * Getters and Setters
	 */
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

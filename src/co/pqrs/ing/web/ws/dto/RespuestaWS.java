package co.pqrs.ing.web.ws.dto;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import co.pqrs.ing.web.db.EncuestaSatisfaccion;
import co.pqrs.ing.web.db.Pregunta;

@XmlRootElement
public class RespuestaWS {
	
	private Long codigo;
	private PreguntaWS pregunta;
	private EncuestaWS encuestaId;
	private String respuesta;
	
	/**
	 * @param jsonRepresentation
	 * @return Retorna un objeto de tipo RespuestaWS a partir de una
	 * cadena en formato json capturada por url
	 */
	public static RespuestaWS fromString(String jsonRepresentation){
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
		RespuestaWS respuesta=null;
		try {
			respuesta=mapper.readValue(jsonRepresentation, RespuestaWS.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return respuesta;
	}
	
	
	public RespuestaWS() {
		super();
	}

	/**
	 * @param codigo
	 * @param pregunta
	 * @param encuestaId
	 * @param respuesta
	 * Constructor de la clase RespuestaWS
	 */
	public RespuestaWS(Long codigo, PreguntaWS pregunta, EncuestaWS encuestaId, String respuesta) {
		super();
		this.codigo = codigo;
		this.pregunta = pregunta;
		this.encuestaId = encuestaId;
		this.respuesta = respuesta;
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
	public PreguntaWS getPregunta() {
		return pregunta;
	}
	public void setPregunta(PreguntaWS pregunta) {
		this.pregunta = pregunta;
	}
	public EncuestaWS getEncuestaId() {
		return encuestaId;
	}
	public void setEncuestaId(EncuestaWS encuestaId) {
		this.encuestaId = encuestaId;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
}

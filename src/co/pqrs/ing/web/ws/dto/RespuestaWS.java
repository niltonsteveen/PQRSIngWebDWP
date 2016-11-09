package co.pqrs.ing.web.ws.dto;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import co.pqrs.ing.web.db.EncuestaSatisfaccion;
import co.pqrs.ing.web.db.Pregunta;

public class RespuestaWS {
	
	private Long codigo;
	private Pregunta pregunta;
	private EncuestaSatisfaccion encuesta;
	private String respuesta;
	
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
		// TODO Auto-generated constructor stub
	}




	public RespuestaWS(Long codigo, Pregunta pregunta, EncuestaSatisfaccion encuesta, String respuesta) {
		super();
		this.codigo = codigo;
		this.pregunta = pregunta;
		this.encuesta = encuesta;
		this.respuesta = respuesta;
	}




	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public EncuestaSatisfaccion getEncuesta() {
		return encuesta;
	}
	public void setEncuesta(EncuestaSatisfaccion encuesta) {
		this.encuesta = encuesta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
}

package co.pqrs.ing.web.ws.dto;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;


@XmlRootElement
public class PreguntaWS {

	
	private Long codigo;
	private PlantillaEncuestaWS plantilla;
	private String pregunta;
	private Boolean obligatoria;
	private Boolean habilitada;
	
	public static PreguntaWS fromString(String jsonRepresentation){
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
		PreguntaWS pregunta=null;
		try {
			pregunta=mapper.readValue(jsonRepresentation, PreguntaWS.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pregunta;
	}
	
	
	
	public PreguntaWS() {
		super();
		// TODO Auto-generated constructor stub
	}



	public PreguntaWS(Long codigo, PlantillaEncuestaWS plantilla, String pregunta, Boolean obligatoria,
			Boolean habilitada) {
		super();
		this.codigo = codigo;
		this.plantilla = plantilla;
		this.pregunta = pregunta;
		this.obligatoria = obligatoria;
		this.habilitada = habilitada;
	}



	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public PlantillaEncuestaWS getPlantilla() {
		return plantilla;
	}
	public void setPlantilla(PlantillaEncuestaWS plantilla) {
		this.plantilla = plantilla;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public Boolean getObligatoria() {
		return obligatoria;
	}
	public void setObligatoria(Boolean obligatoria) {
		this.obligatoria = obligatoria;
	}
	public Boolean getHabilitada() {
		return habilitada;
	}
	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
	}
	
	
	
	
}

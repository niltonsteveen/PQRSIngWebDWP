package co.pqrs.ing.web.ws.dto;

import java.io.IOException;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

@XmlRootElement
public class PlantillaEncuestaWS {

	private Long codigo;
	private String tipo;
	private String nombre;
	private Date fechaCreacion;
	
	/**
	 * @param jsonRepresentation
	 * @return Retorna un objeto de tipo PlantillaEncuestaWS a partir de una
	 * cadena en formato json capturada por url
	 */
	public static PlantillaEncuestaWS fromString(String jsonRepresentation){
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
		PlantillaEncuestaWS plantilla=null;
		try {
			plantilla=mapper.readValue(jsonRepresentation, PlantillaEncuestaWS.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return plantilla;
	}
		
	
	
	public PlantillaEncuestaWS() {
		super();
	}

	/**
	 * @param codigo
	 * @param tipo
	 * @param nombre
	 * @param fechaCreacion
	 * Método constructor de la clase PlantillaEncuestaWS
	 */
	public PlantillaEncuestaWS(Long codigo, String tipo, String nombre, Date fechaCreacion) {
		super();
		this.codigo = codigo;
		this.tipo = tipo;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
		
}

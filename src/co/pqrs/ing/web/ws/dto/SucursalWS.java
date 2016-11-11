package co.pqrs.ing.web.ws.dto;

import java.io.IOException;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

@XmlRootElement
public class SucursalWS {

	private Long codigo;
	private String nombre;
	private String descripcion;
	
	public SucursalWS() {
	}
	
	/**
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * Método constructor de la clase SucursalWS
	 */
	public SucursalWS(Long codigo, String nombre, String descripcion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	/**
	 * @param jsonRepresentation
	 * @return
	 */
	public static SucursalWS fromString(String jsonRepresentation){
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
		SucursalWS sucursal=null;
		try {
			sucursal=mapper.readValue(jsonRepresentation, SucursalWS.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sucursal;
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

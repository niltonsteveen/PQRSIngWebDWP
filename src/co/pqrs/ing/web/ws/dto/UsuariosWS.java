package co.pqrs.ing.web.ws.dto;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import co.pqrs.ing.web.enums.Rol;

/**
 * 
 * @author nilton velez - Nilton.velez@udea.edu.co
 * DTO con los atributos de usuario, sus respectivos setters and getters y 2 constructores
 * para esta clase
 */

@XmlRootElement
public class UsuariosWS {
	private String username;
	private String apellidos;
	private String direccion;
	private String correo;
	private String nombres;
	private String password;
	private String token;
	private Rol rol;
	private Date fechaCreacion;
	private boolean habilitado;
	
	
	public static UsuariosWS fromString(String jsonRepresentation){
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
		UsuariosWS user=null;
		try {
			user=mapper.readValue(jsonRepresentation, UsuariosWS.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * Constructor sin atributos
	 */
	public UsuariosWS() {
	}
	
	/**
	 * 
	 * @param username
	 * @param apellidos
	 * @param direccion
	 * @param correo
	 * @param nombres
	 * @param password
	 * @param token
	 * @param rol
	 * @param fechaCreacion
	 * @param habilitado
	 */
	public UsuariosWS(String username, String apellidos, String direccion, String correo, String nombres,
			String password, String token, Rol rol, Date fechaCreacion, boolean habilitado) {
		super();
		this.username = username;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.correo = correo;
		this.nombres = nombres;
		this.password = password;
		this.token = token;
		this.rol = rol;
		this.fechaCreacion = fechaCreacion;
		this.habilitado = habilitado;
	}
	
	
	
	/**
	 * 
	 * Getters and Setters
	 */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
}

package co.pqrs.ing.web.ws.dto;

import java.io.IOException;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

@XmlRootElement
public class SolicitudPQRWS {
	private Long identificador;
	private String usuarioId;
	private Date fechaCreacion;
	private Date fechaAtencion;
	private Date fechaResolucion;
	private String estado;
	private String usuarioDelegadoId;
	private String usuarioResuelveId;
	private Long sucursalId;
	private String tipo;
	private String descripcion;
	private String respuesta;
	
	/**	
	 * @param jsonRepresentation
	 * @return
	 */
	public static SolicitudPQRWS fromString(String jsonRepresentation){
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
		SolicitudPQRWS pqrs=null;
		try {
			pqrs=mapper.readValue(jsonRepresentation, SolicitudPQRWS.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pqrs;
	}
	
	public SolicitudPQRWS() {
		super();
	}
	
	/**
	 * @param identificador
	 * @param usuarioId
	 * @param fechaCreacion
	 * @param fechaAtencion
	 * @param fechaResolucion
	 * @param estado
	 * @param usuarioDelegadoId
	 * @param usuarioResuelveId
	 * @param sucursalId
	 * @param tipo
	 * @param descripcion
	 * @param respuesta
	 * Método constructor de la clase SolicitudPQRWS
	 */
	public SolicitudPQRWS(Long identificador, String usuarioId, Date fechaCreacion, Date fechaAtencion,
			Date fechaResolucion, String estado, String usuarioDelegadoId, String usuarioResuelveId, Long sucursalId,
			String tipo, String descripcion, String respuesta) {
		super();
		this.identificador = identificador;
		this.usuarioId = usuarioId;
		this.fechaCreacion = fechaCreacion;
		this.fechaAtencion = fechaAtencion;
		this.fechaResolucion = fechaResolucion;
		this.estado = estado;
		this.usuarioDelegadoId = usuarioDelegadoId;
		this.usuarioResuelveId = usuarioResuelveId;
		this.sucursalId = sucursalId;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.respuesta = respuesta;
	}
	public Long getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaAtencion() {
		return fechaAtencion;
	}
	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}
	public Date getFechaResolucion() {
		return fechaResolucion;
	}
	public void setFechaResolucion(Date fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUsuarioDelegadoId() {
		return usuarioDelegadoId;
	}
	public void setUsuarioDelegadoId(String usuarioDelegadoId) {
		this.usuarioDelegadoId = usuarioDelegadoId;
	}
	public String getUsuarioResuelveId() {
		return usuarioResuelveId;
	}
	public void setUsuarioResuelveId(String usuarioResuelveId) {
		this.usuarioResuelveId = usuarioResuelveId;
	}
	public Long getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(Long sucursalId) {
		this.sucursalId = sucursalId;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
		
}

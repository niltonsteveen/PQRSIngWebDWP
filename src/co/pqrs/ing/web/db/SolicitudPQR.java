package co.pqrs.ing.web.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import co.pqrs.ing.web.enums.EstadoPQR;
import co.pqrs.ing.web.enums.TipoPQR;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase tipo Entidad que mapea los atributos de una solicitud de PQR a la base de datos
 * y tiene sus respectivos setters y getters 
 */
@Entity
@Table(name="solicitudes")
public class SolicitudPQR implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="identificador")
	private Long identificador;
	@ManyToOne 
	@JoinColumn(name="usuario", nullable=false)
	private Usuario usuario;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion", nullable=false)
	private Date fechaCreacion;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_atencion")
	private Date fechaAtencion;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_resolucion")
	private Date fechaResolucion;
	@Column(name="estado", nullable=false)
	@Enumerated(EnumType.STRING)
	private EstadoPQR estado;
	@ManyToOne 
	@JoinColumn(name="usuario_delegado")
	private Usuario usuarioDelegado;
	@ManyToOne 
	@JoinColumn(name="usuario_resuelve")
	private Usuario usuarioResuelve;
	@ManyToOne 
	@JoinColumn(name="sucursal")
	private Sucursal sucursal;
	@Column(name="tipo", nullable=false)
	@Enumerated(EnumType.STRING)
	private TipoPQR tipo;
	@Column(name="descripcion", nullable=false)
	@Type(type="text")
	private String descripcion;
	@Column(name="respuesta")
	@Type(type="text")
	private String respuesta;
	
	
	public Long getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	public EstadoPQR getEstado() {
		return estado;
	}
	public void setEstado(EstadoPQR estado) {
		this.estado = estado;
	}
	public Usuario getUsuarioDelegado() {
		return usuarioDelegado;
	}
	public void setUsuarioDelegado(Usuario usuarioDelegado) {
		this.usuarioDelegado = usuarioDelegado;
	}
	public Usuario getUsuarioResuelve() {
		return usuarioResuelve;
	}
	public void setUsuarioResuelve(Usuario usuarioResuelve) {
		this.usuarioResuelve = usuarioResuelve;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public TipoPQR getTipo() {
		return tipo;
	}
	public void setTipo(TipoPQR tipo) {
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

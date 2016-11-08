package co.pqrs.ing.web.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase tipo Entidad que mapea los atributos de una encuesta de satisfaccion a la base de datos y
 * tiene sus respectivos setters y getters 
 */
@Entity
@Table(name="encuesta")
public class EncuestaSatisfaccion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="codigo")
	private Long codigo;
	@ManyToOne 
	@JoinColumn(name="solicitud", nullable=false)
	private SolicitudPQR solicitud;
	@ManyToOne 
	@JoinColumn(name="plantilla", nullable=false)
	private PlantillaEncuesta plantilla;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	@OneToMany
	private List<Pregunta> preguntas;
	@OneToMany
	private List<Respuesta> respuestas;
	
	
	
	
	public List<Respuesta> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public SolicitudPQR getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(SolicitudPQR solicitud) {
		this.solicitud = solicitud;
	}
	public PlantillaEncuesta getPlantilla() {
		return plantilla;
	}
	public void setPlantilla(PlantillaEncuesta plantilla) {
		this.plantilla = plantilla;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	
	
	
	
	
}

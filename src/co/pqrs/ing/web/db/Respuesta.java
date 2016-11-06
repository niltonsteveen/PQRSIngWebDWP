package co.pqrs.ing.web.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase tipo Entidad que mapea los atributos de una respuesta a la base de datos
 * y tiene sus respectivos setters y getters 
 */
@Entity
@Table(name="respuestas")
public class Respuesta implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="codigo")
	private Long codigo;
	@ManyToOne 
	@JoinColumn(name="pregunta", nullable=false)
	private Pregunta pregunta;
	@ManyToOne 
	@JoinColumn(name="encuesta", nullable=false)
	private EncuestaSatisfaccion encuesta;
	@Column(name="respuesta")
	private String respuesta;
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

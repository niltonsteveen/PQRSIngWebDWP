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
 * Clase tipo Entidad que mapea los atributos de una pregunta a la base de datos
 * y tiene sus respectivos setters y getters  
 */
@Entity
@Table(name="preguntas")
public class Pregunta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="codigo")
	private Long codigo;
	@ManyToOne
	@JoinColumn(name="plantilla")
	private PlantillaEncuesta plantilla;
	@Column(name="pregunta", nullable=false)
	private String pregunta;
	@Column(name="obligatoria", nullable=false, columnDefinition="bit(1) default 0")
	private Boolean obligatoria;
	@Column(name="habilitada", nullable=false, columnDefinition="bit(1) default 1")
	private Boolean habilitada;
	
	
	
	public Boolean getHabilitada() {
		return habilitada;
	}
	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public PlantillaEncuesta getPlantilla() {
		return plantilla;
	}
	public void setPlantilla(PlantillaEncuesta plantilla) {
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
	

	
	
	
	

}

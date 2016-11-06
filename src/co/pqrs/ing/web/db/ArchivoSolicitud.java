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
 * Clase tipo Entidad que mapea los atributos de un archivo de solicitud a la base de datos
 * y tiene sus respectivos setters y getters 
 */
@Entity
@Table(name="archivos")
public class ArchivoSolicitud  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="codigo")
	private Long codigo;
	@Column(name="url")
	private String url;
	@Column(name="ruta")
	private String ruta;
	@ManyToOne
	@JoinColumn(name="solicitud")
	private SolicitudPQR solicitud;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public SolicitudPQR getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(SolicitudPQR solicitud) {
		this.solicitud = solicitud;
	}
}

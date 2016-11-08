package co.pqrs.ing.web.logic;

import java.io.UnsupportedEncodingException;
import java.util.List;

import co.pqrs.ing.web.dao.impl.EncuestaSatisfaccionDAO;
import co.pqrs.ing.web.db.EncuestaSatisfaccion;
import co.pqrs.ing.web.db.Pregunta;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.util.SendEmail;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com
 * @version 1.0.0
 * Clase que implementa los metodos de la interfaz EncuestaSatisfaccionBI 
 */
public class EncuestaSatisfaccionBlImpl implements EncuestaSatisfaccionBI {

	private EncuestaSatisfaccionDAO encuestaSatDao;
	private PreguntaBI preguntaBl;

	public EncuestaSatisfaccionDAO getEncuestaSatDao() {
		return encuestaSatDao;
	}

	public void setEncuestaSatDao(EncuestaSatisfaccionDAO encuestaSatDao) {
		this.encuestaSatDao = encuestaSatDao;
	}
	
	public PreguntaBI getPreguntaBl() {
		return preguntaBl;
	}

	public void setPreguntaBl(PreguntaBI preguntaBl) {
		this.preguntaBl = preguntaBl;
	}

	@Override
	public void enviarEncuestaSatisfaccion(EncuestaSatisfaccion encuesta) throws MyDAOException {
		// TODO Auto-generated method stub
		if (encuesta == null) {
			throw new MyDAOException("la encuesta no puede ser nulo");
		} else if (encuesta.getCodigo() == null) {
			throw new MyDAOException("Debe proporcionar un codigo de encuesta");
		} else {

			if (encuesta.getSolicitud() == null) {
				throw new MyDAOException("la encuesta debe estar vinculada a una solicitud",
						new NullPointerException());
			} else if (encuesta.getSolicitud().getUsuario() == null) {
				throw new MyDAOException("la encuesta debe estar vinculada a un usuario", new NullPointerException());
			} else if (encuesta.getPlantilla() == null) {
				throw new MyDAOException("la encuesta debe estar basada en una plantilla", new NullPointerException());
			}
			StringBuilder bodyEmail = new StringBuilder();
			bodyEmail.append("Se�or(a): ");
			bodyEmail.append(encuesta.getSolicitud().getUsuario().getNombres());
			bodyEmail.append(" ");
			bodyEmail.append(encuesta.getSolicitud().getUsuario().getApellidos());
			bodyEmail.append("Para nosotros es importante mejorar cada dia por eso lo invitamos a responder");
			bodyEmail.append("la encuesta pertinente a su solicitud numero ");
			bodyEmail.append(encuesta.getSolicitud().getIdentificador());
			bodyEmail.append("en el link debajo:");
			bodyEmail.append("www.pqrs-udea.com/encuestas/");
			bodyEmail.append("encuesta.jsp?encuesta=");
			bodyEmail.append(encuesta.getCodigo());
			try {
				SendEmail.sendMail(encuesta.getSolicitud().getUsuario().getEmail(),
						"Encuesta de Satisfaccion PQR " + encuesta.getSolicitud().getIdentificador(),
						bodyEmail.toString());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new MyDAOException(
						"Ocurrio un error enviando el email de la encuesta Nro: " + encuesta.getCodigo(), e);
			}
		}
	}

	@Override
	public Long guardarEncuesta(EncuestaSatisfaccion encuesta) throws MyDAOException {
		// TODO Auto-generated method stub
		if (encuesta == null) {
			throw new MyDAOException("la encuesta no puede ser nula");
		} else if (encuesta.getCodigo() == null) {
			throw new MyDAOException("Debe proporcionar un codigo de encuesta");
		} else {

			if (encuesta.getSolicitud() == null) {
				throw new MyDAOException("la encuesta debe estar vinculada a una solicitud",
						new NullPointerException());
			} else if (encuesta.getSolicitud().getUsuario() == null) {
				throw new MyDAOException("la encuesta debe estar vinculada a un usuario", new NullPointerException());
			} else if (encuesta.getPlantilla() == null) {
				throw new MyDAOException("la encuesta debe estar basada en una plantilla", new NullPointerException());
			}
			return (Long) encuestaSatDao.toSaveGetId(encuesta);
		}
	}

	@Override
	public EncuestaSatisfaccion presentarEncuesta(Long identificadorEncuesta) throws MyDAOException {
		// TODO Auto-generated method stub
		if (identificadorEncuesta == null) {
			throw new MyDAOException("el identificador no puede ser nulo", new NullPointerException());
		}
		EncuestaSatisfaccion encuestaSatisfaccion = encuestaSatDao.toGet(identificadorEncuesta);
		if (encuestaSatisfaccion == null) {
			throw new MyDAOException("el identificador de encusta no existe", new NullPointerException());
		}
		List<Pregunta> preguntas = preguntaBl.listarPreguntasByPlantilla(encuestaSatisfaccion.getPlantilla());
		if(preguntas==null|| preguntas.size()==0){
			throw new MyDAOException("no se han definido preguntas para la encuesta");
		}
		encuestaSatisfaccion.setPreguntas(preguntas);
		return encuestaSatisfaccion;

	}

	@Override
	public EncuestaSatisfaccion cargarEncuesta(Long idEncuesta) throws MyDAOException {
		// TODO Auto-generated method stub
		if(idEncuesta==null){
		 throw new MyDAOException("El identificador de la encuesta no puede ser nulo", new NullPointerException());
		}
		return encuestaSatDao.toGet(idEncuesta);
	}
	
	
}

package co.pqrs.ing.web.logic;

import java.util.List;

import co.pqrs.ing.web.dao.impl.EncuestaSatisfaccionDAO;
import co.pqrs.ing.web.dao.impl.RespuestaDAO;
import co.pqrs.ing.web.db.EncuestaSatisfaccion;
import co.pqrs.ing.web.db.Respuesta;
import co.pqrs.ing.web.exception.MyDAOException;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * @version 1.0.0
 * Clase que implementa los metodos de la interfaz RespuestasBI 
 */
public class RespuestasBlImpl implements RespuestasBI {

	private RespuestaDAO respuestaDao;
	private EncuestaSatisfaccionDAO encuestaSatisfacionDao;

	public RespuestaDAO getRespuestaDao() {
		return respuestaDao;
	}

	public void setRespuestaDao(RespuestaDAO respuestaDao) {
		this.respuestaDao = respuestaDao;
	}

	
	public EncuestaSatisfaccionDAO getEncuestaSatisfacionDao() {
		return encuestaSatisfacionDao;
	}

	public void setEncuestaSatisfacionDao(EncuestaSatisfaccionDAO encuestaSatisfacionDao) {
		this.encuestaSatisfacionDao = encuestaSatisfacionDao;
	}

	@Override
	public void guardarRespuestas(List<Respuesta> respuestas) throws MyDAOException {
		// TODO Auto-generated method stub
		if (respuestas == null) {
			throw new MyDAOException("La lista de respuestas no puede ser nula", new NullPointerException());
		} else if (respuestas.size() == 0) {
			throw new MyDAOException("La lista de respuestas no contiene elementos");
		} else {
			for (Respuesta r : respuestas) {
				if (r == null) {
					throw new MyDAOException("Una de las respuestas es nula", new NullPointerException());
				} else if (r.getPregunta() == null) {
					throw new MyDAOException("La respuesta debe coincidir con una pregunta no nula",
							new NullPointerException());
				} else if (r.getRespuesta() == null) {
					throw new MyDAOException("La respuesta no puede ser nula", new NullPointerException());
				} else if ("".equals(r.getRespuesta())) {
					throw new MyDAOException("La respuesta debe estar diligenciada");
				} else if (r.getEncuesta() == null) {
					throw new MyDAOException("La respuesta coincidir con una encuesta no nula",
							new NullPointerException());
				}
			}
			respuestaDao.toSaveList(respuestas);
		}
	}

	@Override
	public List<Respuesta> listarRespuestasByEncuesta(EncuestaSatisfaccion encuesta) throws MyDAOException {
		// TODO Auto-generated method stub
		if (encuesta == null) {
			throw new MyDAOException("La respuesta debe estar diligenciada", new NullPointerException());
		} else if (encuesta.getCodigo() == null) {
			throw new MyDAOException("El codigo de la encuesta no puede ser nulo ", new NullPointerException());
		} else if (encuestaSatisfacionDao.toGet(encuesta.getCodigo()) == null) {
			throw new MyDAOException("La encuesta de satisfaccion no existe ");
		} else {
			return respuestaDao.getByEncuesta(encuesta);
		}
	}

}

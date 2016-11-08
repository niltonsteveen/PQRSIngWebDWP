package co.pqrs.ing.web.dao.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.pqrs.ing.web.dao.impl.SolicitudPQRDAO;
import co.pqrs.ing.web.db.SolicitudPQR;
import co.pqrs.ing.web.db.Usuario;
import co.pqrs.ing.web.enums.EstadoPQR;
import co.pqrs.ing.web.enums.Rol;
import co.pqrs.ing.web.enums.TipoPQR;
import co.pqrs.ing.web.exception.MyDAOException;
import co.pqrs.ing.web.logic.SolicitudPQRBl;
import co.pqrs.ing.web.logic.UsuarioBl;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config.xml"})
public class SolicitudPQRBlTest {
	@Autowired
	SolicitudPQRBl pqr;
	
	@Autowired
	UsuarioBl userBl;
	
	//@Test
	public void testRegistrarPQR() {
		SolicitudPQR solicitud;
		Usuario loged;
		Usuario user;
		try {
			solicitud=new SolicitudPQR();
			solicitud.setFechaCreacion(new Date());
			solicitud.setEstado(EstadoPQR.SIN_ATENDER);
			solicitud.setTipo(TipoPQR.RECLAMO);
			solicitud.setDescripcion("reclamo de algo");
			loged=new Usuario();
			loged.setRol(Rol.ENCARGADO);
			user=userBl.getUserById("niltonsteveen");
			solicitud.setUsuario(user);
			pqr.crearPQR(solicitud, loged);
		} catch (MyDAOException | NullPointerException e) {
			fail(e.getMessage());
		}
	}
	
	//@Test
	public void testCancelarPQR() {
		SolicitudPQR solicitud;
		Usuario loged;
		try {
			solicitud=pqr.getSolicitudById(Long.valueOf("2"));
			loged=new Usuario();
			loged.setRol(Rol.CLIENTE);
			pqr.cancelarPQR(solicitud, loged);
		} catch (MyDAOException e) {
			fail(e.getMessage());
		}
	}
	
	
	
	//@Test
	public void testNotificarPQRS() {
		List<SolicitudPQR> solicitudes;
		Usuario loged;
		try {
			loged=new Usuario();
			loged.setRol(Rol.GERENTE);
			solicitudes=pqr.notificarPQR(loged);
		} catch (MyDAOException e) {
			fail(e.getMessage());
		}
	}

}

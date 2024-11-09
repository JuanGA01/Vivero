package com.services;

import com.model.Credenciales;
import com.model.Ejemplar;
import com.model.Mensaje;

public interface ServicioMensaje {

	boolean crearMensaje (Credenciales credenciales, Ejemplar ejemplar, Mensaje mensaje);
	
	Mensaje VerMensajesDeSeguimientoEjemplar (Ejemplar ejemplar);
	
	String buscarMensajeXEjemplar(Long id);
	
}

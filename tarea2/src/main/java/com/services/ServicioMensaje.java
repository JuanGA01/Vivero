package com.services;

import com.model.Ejemplar;
import com.model.Mensaje;
import com.model.Persona;

public interface ServicioMensaje {

	boolean crearMensaje (Persona persona, Ejemplar ejemplar, Mensaje mensaje);
	
	Mensaje VerMensajesDeSeguimientoEjemplar (Ejemplar ejemplar);
	
}

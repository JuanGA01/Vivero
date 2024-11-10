package com.services;

import java.time.LocalDateTime;

import com.model.Ejemplar;
import com.model.Mensaje;
import com.model.Persona;
import com.model.Planta;

public interface ServicioMensaje {

	String crearMensaje (Persona persona, Ejemplar ejemplar, Mensaje mensaje);
	
	Mensaje VerMensajesDeSeguimientoEjemplar (Ejemplar ejemplar);
	
	String buscarMensajeXEjemplar(Long id);
	
	String listarTodosMensajes();
	
	String listarXPersona(Persona persona);
	
	String listarXRangoFechas(LocalDateTime desde, LocalDateTime hasta);
	
	String listarXTipoPlanta(Planta planta);
	
}

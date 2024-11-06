package com.services;

import com.model.Persona;

public interface ServicioPersona {

	Boolean CorreoExistente(Persona persona);
	
	Long GuardarPersona(Persona persona);
	
}

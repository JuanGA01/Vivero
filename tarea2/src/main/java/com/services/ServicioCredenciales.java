package com.services;

import com.model.Credenciales;

public interface ServicioCredenciales {
	
	String loginUsuario(String usuario, String password);
	
	Boolean UsuarioCorrecto(String usuario, String password);
	
	Boolean UsuarioExistente(String usuario);
	
	String GuardarCredenciales(Long idPersona, Credenciales credenciales);
	
}

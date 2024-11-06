package com.services;

public interface ServicioCredenciales {
	
	String loginUsuario(String usuario, String password);
	
	Boolean UsuarioCorrecto(String usuario, String password);
}

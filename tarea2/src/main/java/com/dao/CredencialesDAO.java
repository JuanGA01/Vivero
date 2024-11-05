package com.dao;

public interface CredencialesDAO {
	
    // Método para verificar si un usuario y contraseña son válidos
    boolean autenticarUsuario(String usuario, String password);
    
    // Método para crear Usuario
    boolean crearUsuario(String usuario, String password);
}

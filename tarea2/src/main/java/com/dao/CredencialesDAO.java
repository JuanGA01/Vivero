package com.dao;

import java.util.Optional;
import com.model.Credenciales;

public interface CredencialesDAO {
	
	//Método para buscar usuarios
	Optional<Credenciales> findByUsuario(String usuario);
	
    // Método para verificar si un usuario y contraseña son válidos
    boolean autenticarUsuario(String usuario, String password);
    
    // Método para crear Usuario
    boolean crearUsuario(Long idPersona, String usuario, String password);

    // Método para obtener credenciales
	Credenciales obtenerCredencialesAutenticadas(String usuario, String password);
    
    
    
}

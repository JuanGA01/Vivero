package com.services;

import com.model.Credenciales;

public interface ServicioCredenciales {

    // Realiza el login de un usuario y devuelve un mensaje.
    String loginUsuario(String usuario, String password);

    // Verifica si las credenciales del usuario son correctas.
    Boolean UsuarioCorrecto(String usuario, String password);

    // Comprueba si el usuario existe en el sistema.
    Boolean UsuarioExistente(String usuario);

    // Guarda las credenciales de un usuario.
    String GuardarCredenciales(Long idPersona, Credenciales credenciales);
    
}

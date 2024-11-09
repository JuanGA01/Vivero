package com.dao;

import java.util.List;
import java.util.TreeMap;
import com.model.Ejemplar;
import com.model.Mensaje;
import com.model.Persona;

public interface MensajeDAO {
	
    boolean insertarMensaje(Mensaje mensaje, Ejemplar ejemplar, Persona persona);
    
    Mensaje obtenerMensajePorId(Long id);
    
    List<Mensaje> obtenerTodosLosMensajes();
    
    boolean actualizarMensaje(Mensaje mensaje);
    
    boolean eliminarMensaje(Long id);
    
    TreeMap<Mensaje, Persona> buscarMensajeXEjemplar(Long ejemplarId);
}

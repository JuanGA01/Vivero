package com.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;
import com.model.Ejemplar;
import com.model.Mensaje;
import com.model.Persona;
import com.model.Planta;

public interface MensajeDAO {
	
    boolean insertarMensaje(Mensaje mensaje, Ejemplar ejemplar, Persona persona);
    
    Mensaje obtenerMensajePorId(Long id);
    
    List<Mensaje> obtenerTodosLosMensajes();
    
    boolean actualizarMensaje(Mensaje mensaje);
    
    boolean eliminarMensaje(Long id);
    
    TreeMap<Mensaje, Persona> buscarMensajeXEjemplar(Long ejemplarId);

	List<Mensaje> obtenerMensajesPorPersona(Persona persona);

	List<Mensaje> obtenerMensajesPorRangoFechas(LocalDateTime desde, LocalDateTime hasta);

	List<Mensaje> obtenerMensajesPorTipoCodigoPlanta(Planta planta);
}

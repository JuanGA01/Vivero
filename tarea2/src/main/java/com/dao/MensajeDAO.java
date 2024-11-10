package com.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;
import com.model.Ejemplar;
import com.model.Mensaje;
import com.model.Persona;
import com.model.Planta;

public interface MensajeDAO {

	// Método para insertar un nuevo mensaje asociado a un ejemplar y una persona
	boolean insertarMensaje(Mensaje mensaje, Ejemplar ejemplar, Persona persona);

	// Método para obtener un mensaje específico mediante su ID
	Mensaje obtenerMensajePorId(Long id);

	// Método para obtener todos los mensajes almacenados
	List<Mensaje> obtenerTodosLosMensajes();

	// Método para actualizar la información de un mensaje existente
	boolean actualizarMensaje(Mensaje mensaje);

	// Método para eliminar un mensaje específico mediante su ID
	boolean eliminarMensaje(Long id);

	// Método para buscar mensajes asociados a un ejemplar específico, devolviendo un TreeMap de mensajes y personas
	TreeMap<Mensaje, Persona> buscarMensajeXEjemplar(Long ejemplarId);

	// Método para obtener todos los mensajes relacionados con una persona específica
	List<Mensaje> obtenerMensajesPorPersona(Persona persona);

	// Método para obtener mensajes dentro de un rango de fechas especificado
	List<Mensaje> obtenerMensajesPorRangoFechas(LocalDateTime desde, LocalDateTime hasta);

	// Método para obtener mensajes asociados a un tipo de planta específico, identificado por el código de planta
	List<Mensaje> obtenerMensajesPorTipoCodigoPlanta(Planta planta);

}


package com.dao;

import java.util.List;
import com.model.Mensaje;

public interface MensajeDAO {
	
	//Método para agregar mensajes
	boolean agregarMensaje(String idEjemplar, String usuario, String mensaje);
	
	//Método para listar mensajes
	 List<Mensaje> listarMensajes();
	
}

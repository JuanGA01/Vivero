package com.services;

import com.model.Credenciales;

public interface ServicioCredenciales {
	
	int insertar(Credenciales credenciales);

	int modificar(Credenciales credenciales);

	int eliminar(Credenciales credenciales);
	
}

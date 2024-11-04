package com.services;

import com.model.Planta;

public interface ServicioPlanta {
	
//Validar planta devuelve booleano
	
	//Insertar planta en BBDD
	//public int insertar (Planta planta) {
		
	//}
	
	int insertar(Planta planta);

	int modificar(Planta planta);

	int eliminar(Planta planta);
	
}

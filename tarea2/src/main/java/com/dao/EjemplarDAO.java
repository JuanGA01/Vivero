package com.dao;

import java.util.List;
import com.model.Planta;

public interface EjemplarDAO {
	
	//Método para registrar un ejemplar
	boolean registrarEjemplar (Planta planta);
	
	//Método para listar ejemplares
	List<String> Ejemplares();
	
}

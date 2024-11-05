package com.dao;

import java.util.List;

public interface EjemplarDAO {
	
	//Método para registrar un ejemplar
	boolean registrarEjemplar ();
	
	//Método para listar ejemplares
	List<String> Ejemplares();
	
}

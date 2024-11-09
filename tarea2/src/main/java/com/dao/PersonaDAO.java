package com.dao;

import java.util.List;
import com.model.Persona;

public interface PersonaDAO {
	
	//Método para crear personas
	boolean crearPersona(Persona persona);
	
	//Mérodo para listar las personas y sus credenciales
	List<Persona> findAll();
	
	//Método para buscar persona por id
	Persona findById(Long id);
	
}

package com.dao;

import java.util.List;
import com.model.Ejemplar;
import com.model.Planta;

public interface EjemplarDAO {

	//Método para registrar un ejemplar
	Ejemplar registrarEjemplar (Planta planta);

	//Método para listar ejemplares
	List<String> Ejemplares();

	//Método para listar ejemplares
	public List<Ejemplar> obtenerEjemplaresPorPlanta(Planta planta);

	//Método para listar ejemplares
	List<Ejemplar> findAll();

}

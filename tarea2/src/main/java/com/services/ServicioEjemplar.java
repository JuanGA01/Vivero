package com.services;

import java.util.Set;
import com.model.Persona;
import com.model.Planta;

public interface ServicioEjemplar {
	
	boolean registrarNuevoEjemplar (Planta planta, Persona persona);
	
	String listarEjemplaresPorTipoDePlanta (Set<Planta> listaABuscar);
	
	String listarEjemplares();
	
	boolean existeEjemplar(Long id);
	
}

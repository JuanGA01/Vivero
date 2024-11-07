package com.services;

import java.util.List;
import com.model.Ejemplar;
import com.model.Planta;

public interface ServicioEjemplar {
	
	boolean registrarNuevoEjemplar (Planta planta);
	
	List<Ejemplar> listarEjemplaresPorTipoDePlanta (Planta planta);
	
	
}

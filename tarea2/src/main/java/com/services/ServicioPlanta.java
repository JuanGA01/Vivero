package com.services;

import java.util.List;
import com.model.Planta;

public interface ServicioPlanta {
	
	public String listaPlantas();
	
	public String InsertarPlanta(Planta planta);
	
	public boolean existePlanta(Planta planta);

	Planta BuscarPlantaXId(Planta planta);
	
}

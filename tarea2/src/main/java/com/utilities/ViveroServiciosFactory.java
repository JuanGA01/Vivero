package com.utilities;

import com.services.ServicioCredenciales;
import com.services.ServicioEjemplar;
import com.services.ServicioPersona;
import com.services.ServicioPlanta;
import servicesImpl.ServicioCredencialesImpl;
import servicesImpl.ServicioEjemplarImpl;
import servicesImpl.ServicioPersonaImpl;
import servicesImpl.ServicioPlantaImpl;

public class ViveroServiciosFactory {
	
	public static ViveroServiciosFactory servicios;
	
	public static ViveroServiciosFactory getServicios() {
		if (servicios == null)
			servicios = new ViveroServiciosFactory();
		return servicios;
	}
	
	public ServicioPlanta getServiciosPlanta() {
		return new ServicioPlantaImpl();
	}
	public ServicioCredenciales getServiciosCredenciales() {
		return new ServicioCredencialesImpl();
	}
	public ServicioPersona getServiciosPersona() {
		return new ServicioPersonaImpl();
	}
	public ServicioEjemplar getServicioEjemplar() {
		return new ServicioEjemplarImpl();
	}

}

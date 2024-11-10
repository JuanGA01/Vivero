package com.utilities;

import com.services.ServicioCredenciales;
import com.services.ServicioEjemplar;
import com.services.ServicioMensaje;
import com.services.ServicioPersona;
import com.services.ServicioPlanta;
import servicesImpl.ServicioCredencialesImpl;
import servicesImpl.ServicioEjemplarImpl;
import servicesImpl.ServicioMensajeImpl;
import servicesImpl.ServicioPersonaImpl;
import servicesImpl.ServicioPlantaImpl;

public class ViveroServiciosFactory { 

	// Instancia Singleton de ViveroServiciosFactory
	public static ViveroServiciosFactory servicios;

	// Método para obtener la instancia Singleton de ViveroServiciosFactory
	public static ViveroServiciosFactory getServicios() {
		if (servicios == null)
			servicios = new ViveroServiciosFactory();
		return servicios;
	}

	// Método para obtener una instancia de ServicioPlanta
	public ServicioPlanta getServiciosPlanta() {
		return new ServicioPlantaImpl();
	}

	// Método para obtener una instancia de ServicioCredenciales
	public ServicioCredenciales getServiciosCredenciales() {
		return new ServicioCredencialesImpl();
	}

	// Método para obtener una instancia de ServicioPersona
	public ServicioPersona getServiciosPersona() {
		return new ServicioPersonaImpl();
	}

	// Método para obtener una instancia de ServicioEjemplar
	public ServicioEjemplar getServicioEjemplar() {
		return new ServicioEjemplarImpl();
	}

	// Método para obtener una instancia de ServicioMensaje
	public ServicioMensaje getServicioMensaje() {
		return new ServicioMensajeImpl();
	}

}


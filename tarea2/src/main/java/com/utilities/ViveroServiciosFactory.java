package com.utilities;

import com.services.ServicioPlanta;

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
	

}

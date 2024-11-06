package main;

import facade.ViveroFacade;

public class Main {
	
	public static void main(String[] args) {
		ViveroFacade portal = ViveroFacade.getPortal();
		portal. mostrarMenuGestionPrincipal();
	}
	
}
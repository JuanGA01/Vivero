package facade;

import java.util.Scanner;
import com.services.ServicioPlanta;
import com.utilities.MySqlDAOFactory;
import com.utilities.ViveroServiciosFactory;

public class ViveroFacade {
	
	private static  ViveroFacade portal;
	
	MySqlDAOFactory factoriaDAO = MySqlDAOFactory.getCon();
	ViveroServiciosFactory factoriaServicios = ViveroServiciosFactory.getServicios(); 
	
	ServicioPlanta plantServ = factoriaServicios.getServiciosPlanta();

	
	public static ViveroFacade getPortal() {
		if (portal==null)
			portal=new ViveroFacade();
		return portal;
	}
	
	public void mostrarMenuPrincipal() {
        System.out.println("\n---- Vivero System ----");
        System.out.println("1. Ver Plantas (Invitado)");
        System.out.println("2. Autenticarse");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
	}
	
	public void mostrarMenuGestionPrincipal() {
		 Scanner scanner = new Scanner(System.in);
	        int option;
	        
	        do {
	        	mostrarMenuPrincipal();
	            option = scanner.nextInt();
	            scanner.nextLine();  // Consume newline
	            
	            switch (option) {
	                case 1:
	                	System.out.println("1");
	                    break;
	                case 2:
	                	System.out.println("2");
	                    break;
	                case 3:
	                    System.out.println("Saliendo del sistema...");
	                    break;
	                default:
	                    System.out.println("Opción inválida. Intente de nuevo.");
	            }
	        } while (option != 3);
	        
	        scanner.close();
	    }
	

	
}

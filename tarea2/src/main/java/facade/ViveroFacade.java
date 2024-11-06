package facade;

import java.util.Scanner;
import com.services.ServicioCredenciales;
import com.services.ServicioPlanta;
import com.utilities.MySqlDAOFactory;
import com.utilities.ViveroServiciosFactory;

public class ViveroFacade {
	
	private static  ViveroFacade portal;
	Scanner scanner = new Scanner(System.in);
	
	MySqlDAOFactory factoriaDAO = MySqlDAOFactory.getCon();
	ViveroServiciosFactory factoriaServicios = ViveroServiciosFactory.getServicios(); 
	
	//Servicios
	ServicioPlanta plantServ = factoriaServicios.getServiciosPlanta();
	ServicioCredenciales credencialesServ = factoriaServicios.getServiciosCredenciales();

	
	public static ViveroFacade getPortal() {
		if (portal==null)
			portal=new ViveroFacade();
		return portal;
	}
	
	public void mostrarMenuGestionPrincipal() {
	        int option;
	        
	        do {
	            System.out.println("\n---- Vivero ----");
	            System.out.println("1. Ver Plantas (Invitado)");
	            System.out.println("2. Autenticarse");
	            System.out.println("3. Salir");
	            System.out.print("Seleccione una opción: ");
	            option = scanner.nextInt();
	            scanner.nextLine();  // Consume newline
	            
	            switch (option) {
	                case 1:
	                   System.out.println(plantServ.listaPlantas());
	                   break;
	                case 2:
	                   login();
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
	
	
    private void login() {
    	// Pedir al usuario que ingrese su nombre de usuario y contraseña
        System.out.print("Introduce el nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Introduce la contraseña: ");
        String password = scanner.nextLine();
        System.out.println(credencialesServ.loginUsuario(usuario, password));
        if(credencialesServ.UsuarioCorrecto(usuario, password) && usuario.equals("admin")) {
        	menuAdmin();
        }else if(credencialesServ.UsuarioCorrecto(usuario, password) && !usuario.equals("admin")){
        	menuPersonal();
        }
    }
    
    //Menú admin
    private void menuAdmin() {
        int option;
        do {
            System.out.println("\n---- Admin Menu ----");
            System.out.println("1. Ver Plantas");
            System.out.println("2. Gestionar usuarios");
            System.out.println("3. Salir al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                	System.out.println(plantServ.listaPlantas());
                    break;
                case 2:
                    System.out.println("Funcionalidad de gestión de usuarios...");
                    break;
                case 3:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 3);
    }
    
    private void menuPersonal() {
        int option;
        do {
            System.out.println("\n---- Menu Personal ----");
            System.out.println("1. Ver Plantas");
            System.out.println("2. Actualizar información de plantas");
            System.out.println("3. Salir al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                    break;
                case 2:
                    System.out.println("Funcionalidad de actualización de plantas...");
                    break;
                case 3:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 3);
    }
	
}

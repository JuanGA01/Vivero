package facade;

import java.util.Scanner;
import com.model.Credenciales;
import com.model.Persona;
import com.model.Planta;
import com.services.ServicioCredenciales;
import com.services.ServicioEjemplar;
import com.services.ServicioPersona;
import com.services.ServicioPlanta;
import com.utilities.MySqlDAOFactory;
import com.utilities.Utilities;
import com.utilities.ViveroServiciosFactory;

public class ViveroFacade {
	
	private static  ViveroFacade portal;
	Scanner scanner = new Scanner(System.in);
	
	MySqlDAOFactory factoriaDAO = MySqlDAOFactory.getCon();
	ViveroServiciosFactory factoriaServicios = ViveroServiciosFactory.getServicios(); 
	
	//Servicios
	ServicioPlanta plantServ = factoriaServicios.getServiciosPlanta();
	ServicioCredenciales credencialesServ = factoriaServicios.getServiciosCredenciales();
	ServicioPersona personaServ = factoriaServicios.getServiciosPersona();
	ServicioEjemplar ejemplarServ = factoriaServicios.getServicioEjemplar();

	
	public static ViveroFacade getPortal() {
		if (portal==null)
			portal=new ViveroFacade();
		return portal;
	}
	
	//Menú principal
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
	

    //Menú admin
    private void menuAdmin() {
        int option;
        do {
            System.out.println("\n---- Admin Menu ----");
            System.out.println("1. Ver Plantas");
            System.out.println("2. Gestionar usuarios");
            System.out.println("3. Insertar/modificar planta");
            System.out.println("4. Salir al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                	System.out.println(plantServ.listaPlantas());
                    break;
                case 2:
                    System.out.println("-----------------------------------------");
                    registroUsuarios();
                    break;
                case 3:
                	System.out.println("-----------------------------------------");
                    insertarPlanta();
                    break;
                case 4:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 4);
    }
    
    //Menú para los no administradores
    private void menuPersonal() {
        int option;
        do {
            System.out.println("\n---- Menu Personal ----");
            System.out.println("1. Ver Plantas");
            System.out.println("2. Registrar un nuevo ejemplar");
            System.out.println("3. Filtrar ejemplares por tipo de planta");
            System.out.println("4. Ver mensajes de seguimiento de un ejemplar");
            System.out.println("5. Añadir mensajes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                	System.out.println(plantServ.listaPlantas());
                    break;
                case 2:
                	registrarEjemplar();
                    break;
                case 3:
                   
                    break;
                case 4:
                    
                    break;
                case 5:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 6);
    }
	
	//Logueo usuarios
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
    
    
    //Registro usuarios (ADMIN)
    private void registroUsuarios() {
    	Persona nuevoUsuario = new Persona();
    	Credenciales credencialesNUsuario = new Credenciales();
    	System.out.println("Introduce el nombre: ");
    	nuevoUsuario.setNombre(scanner.nextLine());
    	do {
        	System.out.println("Introduce el correo de el nuevo usuario");
        	nuevoUsuario.setEmail(scanner.nextLine());
        	if(personaServ.CorreoExistente(nuevoUsuario)) {
        		System.out.println("Ese correo ya está registrado");
        	}
        	if(!Utilities.ValidEmail(nuevoUsuario.getEmail())) {
        		System.out.println("Correo no válido");
        	}
    	}while (!Utilities.ValidEmail(nuevoUsuario.getEmail()) || personaServ.CorreoExistente(nuevoUsuario) );
    	do {
        	System.out.println("Introduce el nombre de usuario: ");
        	credencialesNUsuario.setUsuario(scanner.nextLine());
        	if (credencialesServ.UsuarioExistente(credencialesNUsuario.getUsuario())) {
        		System.out.println("Ese nombre de usuario ya existe");
        	}
    	} while (credencialesServ.UsuarioExistente(credencialesNUsuario.getUsuario()));
    	System.out.println("Introduce la contraseña: ");
    	credencialesNUsuario.setPassword(scanner.nextLine());
    	nuevoUsuario.setCrecenciales(credencialesNUsuario);
    	
    	//Guardamos la persona, después vemos el id que se le ha asignado y se lo asignamos a las credenciales
    	//para mantener la coherencia en la BBDD
    	Long nuevoUsuarioID = personaServ.GuardarPersona(nuevoUsuario);
    	System.out.println(credencialesServ.GuardarCredenciales(nuevoUsuarioID, nuevoUsuario.getCrecenciales()));
    }
    
    //Insertar planta
    private void insertarPlanta() {
    	Planta planta = new Planta();
    	System.out.println("Introduce el código de la planta: ");
    	planta.setCodigo(scanner.nextLine());
    	System.out.println("Introduce el nombre común de la nueva planta: ");
    	planta.setNombreComun(scanner.nextLine());
    	System.out.println("Introduce el nombre científico de la nueva planta: ");
    	planta.setNombreCientifico(scanner.nextLine());
    	System.out.println(plantServ.InsertarPlanta(planta));
    	}
    
  //Insertar planta
    private void registrarEjemplar() {
    	Planta planta = new Planta();
    	do {
    		System.out.println("Introduce el código de la planta: ");
    		planta = plantServ.BuscarPlantaXId(planta);
    		if (planta.equals(null)) {
    			System.out.println("Introduce un código válido");
    		}
    	} while (planta.equals(null));
    	ejemplarServ.registrarNuevoEjemplar(planta);
    	//Aqui
    }
    
}

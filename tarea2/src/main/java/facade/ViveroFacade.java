package facade;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import com.model.Credenciales;
import com.model.Ejemplar;
import com.model.Mensaje;
import com.model.Persona;
import com.model.Planta;
import com.mysql.cj.jdbc.jmx.LoadBalanceConnectionGroupManager;
import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;
import com.services.ServicioCredenciales;
import com.services.ServicioEjemplar;
import com.services.ServicioMensaje;
import com.services.ServicioPersona;
import com.services.ServicioPlanta;
import com.utilities.MySqlDAOFactory;
import com.utilities.Utilities;
import com.utilities.ViveroServiciosFactory;

public class ViveroFacade {
	
	private static ViveroFacade portal;
	Scanner scanner = new Scanner(System.in);
	
	MySqlDAOFactory factoriaDAO = MySqlDAOFactory.getCon();
	ViveroServiciosFactory factoriaServicios = ViveroServiciosFactory.getServicios(); 
	
	//Servicios
	ServicioPlanta plantServ = factoriaServicios.getServiciosPlanta();
	ServicioCredenciales credencialesServ = factoriaServicios.getServiciosCredenciales();
	ServicioPersona personaServ = factoriaServicios.getServiciosPersona();
	ServicioEjemplar ejemplarServ = factoriaServicios.getServicioEjemplar();
	ServicioMensaje mensajeServ = factoriaServicios.getServicioMensaje();

	//Método para instanciar el portal
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
    private void menuPersonal(Persona persona) {
        int option;
        do {
            System.out.println("\n---- Menu Personal ----");
            System.out.println("1. Ver Plantas");
            System.out.println("2. Registrar un nuevo ejemplar");
            System.out.println("3. Filtrar ejemplares por tipo de planta");
            System.out.println("4. Ver mensajes de seguimiento de un ejemplar");
            System.out.println("5. Añadir mensajes");
            System.out.println("6. Ver mensajes");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                	System.out.println(plantServ.listaPlantas());
                    break;
                case 2:
                	registrarEjemplar(persona);
                    break;
                case 3:
                	buscarEjemplaresXtipoDePlanta();
                    break;
                case 4:
                	VerMensajesSeguimientoEjemplar();
                    break;
                case 5:
                	añadirMensajes(persona);
                	break;
                case 6:
                	menuMensajesPersona(persona);
                    break;
                case 7:
                	System.out.println("Regresando al menú principal...");
                	break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 7);
    }
	
   //Menú para listar mensajes (NO ADMINISTRADORES)
    private void menuMensajesPersona(Persona persona) {
        int option;
        do {
            System.out.println("\n---- Menu Mensajes ----");
            System.out.println("1. Ver Mensajes");
            System.out.println("2. Ver mensajes por persona");
            System.out.println("3. Ver mensajes por rango de fechas");
            System.out.println("4. Ver mensajes por tipo específico de planta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                	System.out.println(mensajeServ.listarTodosMensajes());
                    break;
                case 2:
                	mensajesXPersona();
                    break;
                case 3:
                	mensajesXFechas();
                    break;
                case 4:
                	mensajesXPlanta();
                    break;
                case 5:
                	System.out.println("Regresando al menú principal...");
                	break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 5);
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
        	menuPersonal(factoriaDAO.getPersonaDAO().findById(factoriaDAO.getCredencialesDAO().obtenerCredencialesAutenticadas(usuario, password).getId()));
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
    private void registrarEjemplar(Persona persona) {
    	Planta planta = new Planta();
    	System.out.println("Introduce el código de la planta: ");
    	do {
    		 planta = new Planta();
    		planta.setCodigo(scanner.nextLine());
    		planta = plantServ.BuscarPlantaXId(planta);
    		if (planta == null) {
    			System.out.println("Introduce un código válido: ");
    		}
    	} while (planta == null);
    	ejemplarServ.registrarNuevoEjemplar(planta , persona);
    }
    
    //Buscar ejemplares por tipo de planta
    private void buscarEjemplaresXtipoDePlanta() {
    	Set<Planta> plantas = new HashSet<Planta>();
    	int pls;
    	System.out.println("Introduce cuantas plantas vas a añadir");
    	pls = scanner.nextInt();
		do {
			Planta planta = new Planta();
			System.out.println("Introduce el código de la planta: ");
			do {
				planta = new Planta();
				planta.setCodigo(scanner.nextLine());
				planta = plantServ.BuscarPlantaXId(planta);
				if (planta == null) {
					System.out.println("Introduce un código válido: ");
				}
			} while (planta == null);
			plantas.add(planta);
			pls = pls - 1;
		} while (pls != 0);
		System.out.println(ejemplarServ.listarEjemplaresPorTipoDePlanta(plantas));
    }
  	
  	private void VerMensajesSeguimientoEjemplar() {
  		Long id = (long) 0;
  		System.out.println();
  		System.out.println("Ejemplares: ");
  		System.out.println(ejemplarServ.listarEjemplares());
  		do {
  			System.out.println("Introduce la ID del ejemplar: ");
  			id = scanner.nextLong();
  			if (!ejemplarServ.existeEjemplar(id)) {
  				System.out.println("Introduce la ID correcta: ");
  			}
  		}while (!ejemplarServ.existeEjemplar(id));
  		System.out.println();
  		System.out.println(mensajeServ.buscarMensajeXEjemplar(id));
  		
  	}
  	
  	//Método para añadir mensajes
	private void añadirMensajes(Persona persona) {
		Mensaje mensaje = new Mensaje();
		Ejemplar ejemplar = new Ejemplar();
		do {
  			System.out.println("Introduce la ID del ejemplar: ");
  			ejemplar.setId(scanner.nextLong());
  			scanner.nextLine(); 
  			if (!ejemplarServ.existeEjemplar(ejemplar.getId())) {
  				System.out.println("Introduce la ID correcta: ");
  			}
  		}while (!ejemplarServ.existeEjemplar(ejemplar.getId()));
		System.out.println("Introduce el mensaje a almacenar: ");
		mensaje.setMensaje(scanner.nextLine());
		mensaje.setFechahora(LocalDateTime.now());
		System.out.println(mensajeServ.crearMensaje(persona, ejemplar, mensaje));
		
	}

	
	private void mensajesXPlanta() {
		Planta planta;
		System.out.println("Introduce el código de la planta de la que quieres ver sus mensajes: ");
		do {
			planta = new Planta();
			planta.setCodigo(scanner.nextLine());
			planta = plantServ.BuscarPlantaXId(planta);
			if (planta == null) {
				System.out.println("Introduce un código válido: ");
			}
		} while (planta == null);
		System.out.println("Mensajes relativos a la planta " + planta.getNombreComun());
		System.out.println(mensajeServ.listarXTipoPlanta(planta));
	
}

	private void mensajesXFechas() {
		System.out.println("Introduce la primera fecha: ");
		LocalDateTime fecha1 = Utilities.pedirFechaHora(scanner.nextLine(), scanner);
		System.out.println("Introduce segunda fecha: ");
		LocalDateTime fecha2 = Utilities.pedirFechaHora(scanner.nextLine(), scanner);
		System.out.println("Mensajes relativos acontecidos entre esas fechas: ");
		if (fecha1.isBefore(fecha2)) {
			System.out.println(mensajeServ.listarXRangoFechas(fecha1, fecha2));
		}else {
			System.out.println(mensajeServ.listarXRangoFechas(fecha2, fecha1));
		}
	}

	private void mensajesXPersona() {
		Persona persona = new Persona();
		System.out.println("Introduce el código de la persona de la que quieres ver sus mensajes: ");
		do {
			persona.setId(scanner.nextLong());
			scanner.nextLine();
			persona = factoriaDAO.getPersonaDAO().findById(persona.getId());
			if (persona == null) {
				System.out.println("Introduce un código válido: ");
			}
		} while (persona == null);
		System.out.println("Mensajes relativos a " + persona.getNombre());
		System.out.println(mensajeServ.listarXPersona(persona));
	}
    
}

package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import DAO.PlantaDAO;
import DAO.PlantaDAOImpl;
import modelo.Credenciales;
import modelo.Planta;

public class Main {
	 // Establecer conexión a la base de datos
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/tarea2_juan";
    private static String jdbcUser = "root";
    private static String jdbcPassword = "";
	
	
	
	
	
	
	
	
	
    
    // Lista para almacenar credenciales de los usuarios
    private static List<Credenciales> listaCredenciales = new ArrayList<Credenciales>();
    
    // Lista para almacenar las plantas del vivero
    private static List<Planta> listaPlantas = new ArrayList<>();
    
    // Rol actual del usuario (inicialmente invitado)
    private static String usuario = "invitado"; 

    public static void main(String[] args) {
        // Initialize personnel credentials (can be loaded from a database in real applications)
    	Credenciales a = new Credenciales();
    	listaCredenciales.add(a);

        Scanner scanner = new Scanner(System.in);
        int option;
        
        do {
            // Display main menu
            System.out.println("\n---- Vivero System ----");
            System.out.println("1. Ver Plantas (Invitado)");
            System.out.println("2. Autenticarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (option) {
                case 1:
                    verPlantas();
                    break;
                case 2:
                    login(scanner);
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

    // CU1: Ver listado de plantas
    private static void verPlantas() {
        
        //saco la lista de plantas
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            PlantaDAO plantaDAO = new PlantaDAOImpl(connection);

            // Listar todas las plantas
            System.out.println("Listando plantas:");
            List<Planta> plantas = plantaDAO.listarPlantas();
            for (Planta planta : plantas) {
                System.out.println(planta.getCodigo() + " - " + planta.getNombreComun() + " - " + planta.getNombreCientifico());
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // CU2: Login for user authentication
    private static void login(Scanner scanner) {
        // Prompt for username and password
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        
        // Check for admin login
        
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
//            PlantaDAO plantaDAO = new PlantaDAOImpl(connection);
//
//            // Listar todas las plantas
//            System.out.println("Listando plantas:");
//            List<Planta> plantas = plantaDAO.listarPlantas();
//            for (Planta planta : plantas) {
//                System.out.println(planta.getCodigo() + " - " + planta.getNombreComun() + " - " + planta.getNombreCientifico());
//            }
//        } catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        // Check for password
        
        
        // Unknown user

        
    }

    // Menu for Admin (full access)
    private static void showAdminMenu(Scanner scanner) {
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
                    verPlantas(); // Admin can also view plants
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

    // Menu for Personnel (restricted access)
    private static void showPersonnelMenu(Scanner scanner) {
        int option;
        do {
            System.out.println("\n---- Personnel Menu ----");
            System.out.println("1. Ver Plantas");
            System.out.println("2. Actualizar información de plantas");
            System.out.println("3. Salir al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                    verPlantas(); // Personnel can also view plants
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
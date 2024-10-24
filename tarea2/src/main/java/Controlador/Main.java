package Controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import modelo.Credenciales;
import modelo.Planta;

public class Main {

	// Credenciales del administrador
    private static final String USUARIO_ADMIN = "admin";
    private static final String CONTRASEÑA_ADMIN = "admin";
    
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
        // Sort the list of plants alphabetically
        System.out.println("\nListado de plantas (alfabéticamente):");
        for (Planta planta : listaPlantas) {
            System.out.println(planta.toString());
        }
    }

    // CU2: Login for user authentication
    private static void login(Scanner scanner) {
        // Prompt for username and password
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        
        // Check for admin login
        if (username.equals(USUARIO_ADMIN)) {
            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();
            if (password.equals(CONTRASEÑA_ADMIN)) {
            	usuario = "admin";
                System.out.println("Autenticado como Admin. Acceso completo otorgado.");
                showAdminMenu(scanner);
            } else {
                System.out.println("Contraseña incorrecta para Admin. Acceso denegado.");
            }
        } 
        // Check for personnel login
//        else if (listaCredenciales.contains(Credenciales.) {
//            System.out.print("Ingrese su contraseña: ");
//            String password = scanner.nextLine();
//            if (listaCredenciales.get(username).equals(password)) {
//            	usuario = "personal";
//                System.out.println("Autenticado como Personal. Acceso a funcionalidades del personal otorgado.");
//                showPersonnelMenu(scanner);
//            } else {
//                System.out.println("Contraseña incorrecta. Acceso denegado.");
//            }
//        } 
        // Unknown user
        else {
            System.out.println("Usuario no reconocido. Acceso denegado.");
        }
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
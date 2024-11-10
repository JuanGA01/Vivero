package com.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Utilities {
	
	// Método para validar correo
	public static boolean ValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email != null && email.matches(emailRegex);
    }
	
	// Método para convertir un String a int
    public static int pedirEntero(String input, Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Por favor, introduce un número entero: ");
                input = scanner.nextLine();
            }
        }
    }

    // Método para convertir un String a long
    public static long pedirLong(String input, Scanner scanner) {
        while (true) {
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.print("Por favor, introduce un número de tipo long: ");
                input = scanner.nextLine();
            }
        }
    }

    // Método para convertir un String a LocalDateTime
    public static LocalDateTime pedirFechaHora(String input, Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (true) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.print("Por favor, introduce una fecha y hora válida (formato: yyyy-MM-dd HH:mm): ");
                input = scanner.nextLine();
            }
        }
    }
	
}

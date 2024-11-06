package com.utilities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.model.Persona;

public class Utilities {
	
	// Método para validar correo
	public static boolean ValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email != null && email.matches(emailRegex);
    }
	

	
	
    public static boolean leerBoolean() {
        boolean ret;
        Scanner in;
        char resp;
        do {
            System.out.println("Pulse s para Sí o n para No");
            in = new Scanner(System.in, "ISO-8859-1");
            resp = in.nextLine().charAt(0);
            if (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N') {
                System.out.println("Valor introducido incorrecto.");
            }
        } while (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N');
        if (resp == 's' || resp != 'S') {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }

    /**
     * Función que pide al usuario que introduzca un valor decimal por la
     * entrada estándar. Si el formato introducido no es correcto, avisa al
     * usuario y le vuelve a pedir que lo introduzca de nuevo.
     *
     * @return el valor double introducido por el usuario
     */
    public static double leerDouble() {
        double ret = 0.0;
        boolean correcto = false;
        Scanner in;
        do {
            System.out.println("Introduzca un valor decimal (xx.xx)");
            in = new Scanner(System.in, "ISO-8859-1");
            try {
                ret = in.nextDouble();
                correcto = true;
            } catch (InputMismatchException ime) {
                System.out.println("Formato introducido incorrecto.");
                correcto = false;
            }
        } while (!correcto);
        return ret;
    }

    /**
     * Función que pide al usuario que introduce un valor para una fecha a
     * partir de 3 enteros para el día, mes y año respectivamente. Si los
     * valores introducidos no producen una fecha correcta, avisa al usuario y
     * le pide que los introduzca de nuevo. Si no lo consigue, devolverá null
     *
     * @return una fecha de la clase java.sql.Date o null si hay error
     */
    public static java.sql.Date leerFecha() {
        Date ret = null;
        int dia, mes, anio;
        boolean correcto = false;
        Scanner in;
        do {
            System.out.println("Introduzca un valor para el día (1...31)");
            in = new Scanner(System.in, "ISO-8859-1");
            dia = in.nextInt();
            System.out.println("Introduzca un valor para el mes (1...12)");
            in = new Scanner(System.in, "ISO-8859-1");
            mes = in.nextInt();
            System.out.println("Introduzca un valor para el año");
            in = new Scanner(System.in, "ISO-8859-1");
            anio = in.nextInt();

            try {
                ret = Date.valueOf(LocalDate.of(anio, mes, dia));
                correcto = true;
            } catch (Exception e) {
                System.out.println("Fecha introducida incorrecta.");
                correcto = false;
            }
        } while (!correcto);
        return ret;
    }
}

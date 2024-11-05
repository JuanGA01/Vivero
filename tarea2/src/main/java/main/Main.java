package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.dao.CredencialesDAO;
import com.dao.PlantaDAO;
import com.daoImpl.CredencialesDAOImpl;
import com.daoImpl.PlantaDAOImpl;
import com.model.Credenciales;
import com.model.Planta;

import facade.ViveroFacade;

public class Main {
	
	public static void main(String[] args) {
		
		ViveroFacade portal = ViveroFacade.getPortal();


		Scanner in = new Scanner(System.in);
		System.out.println("Programa de gestion de un invernadero");

		int opcion = 0;
		do {
			portal.mostrarMenuPrincipal();
			opcion = in.nextInt();
			if (opcion < 1 || opcion > 8) {
				System.out.println("Opcion incorrecta.");
				continue;
			}
			switch (opcion) {
			case 1:
				//portal.mostrarMenuGestionPlantas();
				break;
			case 2:
				//portal.mostrarMenuGestionEjemplares();
				break;
			case 3:
				//portal.mostrarMenuGestionParcelas();
				break;
			case 4:
				//portal.mostrarMenuGestionLocalizaciones();
				break;
			case 5:
				//portal.comprarEjemplarDePlanta();
				break; // Comprar un ejemplar de una planta.
			case 6:
				//portal.plantarEjemplar();
				break; // Plantar un ejemplar.
			case 7:
				//portal.establerAreasDePlantacion();
				break; // Establecer áreas de plantación.
			case 8:
				break;
			}
		} while (opcion != 8);
		System.out.println("Fin del programa");
	}

}
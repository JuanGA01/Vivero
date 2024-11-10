package com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import com.dao.CredencialesDAO;
import com.dao.EjemplarDAO;
import com.dao.MensajeDAO;
import com.dao.PersonaDAO;
import com.dao.PlantaDAO;
import com.daoImpl.CredencialesDAOImpl;
import com.daoImpl.EjemplarDAOImpl;
import com.daoImpl.MensajeDAOImpl;
import com.daoImpl.PersonaDAOImpl;
import com.daoImpl.PlantaDAOImpl;
import com.mysql.cj.jdbc.MysqlDataSource;

public class MySqlDAOFactory {

	// Objeto Connection para gestionar la conexión con la base de datos
	private Connection connection;

	// Instancia Singleton de MySqlDAOFactory
	private static MySqlDAOFactory f;

	// Constructor privado que implementa el patrón Factory y realiza la conexión
	private MySqlDAOFactory() {
		Properties prop = new Properties();
		MysqlDataSource m = new MysqlDataSource();
		FileInputStream fis;

		// Abrimos un canal de lectura al archivo de propiedades
		try {
			fis = new FileInputStream("src/main/resources/icons/db.properties");

			// Cargamos la información del archivo de propiedades
			prop.load(fis);

			// Asignamos las propiedades leídas (URL, usuario y contraseña) al origen de datos
			m.setUrl(prop.getProperty("url"));
			m.setUser(prop.getProperty("usuario"));
			m.setPassword(prop.getProperty("password"));

			// Obtenemos la conexión a la base de datos
			connection = m.getConnection();

		} catch (FileNotFoundException e) {
			System.out.println("Error al acceder al archivo de propiedades: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer las propiedades del archivo: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: verifique usuario y contraseña.");
		}

	}

	// El patrón Factory proporciona métodos que devuelven objetos complejos 
	// inyectando la conexión obtenida en ellos
	public CredencialesDAO getCredencialesDAO() {
		return new CredencialesDAOImpl(connection);
	}

	public PlantaDAO getPlantaDAO() {
		return new PlantaDAOImpl(connection);
	}

	public PersonaDAO getPersonaDAO() {
		return new PersonaDAOImpl(connection);
	}

	public EjemplarDAO getEjemplaresDAO() {
		return new EjemplarDAOImpl(connection);
	}

	public MensajeDAO getMensajeDAO() {
		return new MensajeDAOImpl(connection);
	}

	// Método estático para obtener la instancia Singleton de MySqlDAOFactory
	public static MySqlDAOFactory getCon() {
		if (f == null)
			f = new MySqlDAOFactory();
		return f;
	}


}


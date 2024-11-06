package com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import com.dao.CredencialesDAO;
import com.dao.PlantaDAO;
import com.daoImpl.CredencialesDAOImpl;
import com.daoImpl.PlantaDAOImpl;
import com.mysql.cj.jdbc.MysqlDataSource;

public class MySqlDAOFactory {

	private Connection connection;

	private static MySqlDAOFactory f;

	// el patron factory realiza la conexión
	private MySqlDAOFactory() {
		Properties prop = new Properties();
		MysqlDataSource m = new MysqlDataSource();
		FileInputStream fis;

		// Abrimos un canal de lectura al fichero de texto plano
		try {
			fis = new FileInputStream("src/main/resources/icons/db.properties");

			// cargamos la informacion del fichero properties
			prop.load(fis);

			// asignamos al origen de datos las propiedades leidas del fichero properties
			m.setUrl(prop.getProperty("url"));
			m.setUser(prop.getProperty("usuario"));
			m.setPassword(prop.getProperty("password"));

			// obtengo la conexion
			 connection = m.getConnection();

		} catch (FileNotFoundException e) {
			System.out.println("Error al acceder al fichero properties " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer las propiedades del fichero properties" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos:usuario,password....");
		}
	}

	// El patron factory proporciona metodos que devuelven los objetos complejos
	// donde inyecta la conexión
	public CredencialesDAO getCredencialesDAO() {
		return new CredencialesDAOImpl(connection);
	}
	
	public PlantaDAO getPlantaDAO() {
		return new PlantaDAOImpl(connection);
	}

	public static MySqlDAOFactory getCon() {
		if (f==null)
			f=new MySqlDAOFactory();
		return f;
	}
	
}

package com.daoImpl;

import java.util.List;
import com.dao.PersonaDAO;
import com.model.Credenciales;
import com.model.Persona;
import java.sql.*;
import java.util.ArrayList;

public class PersonaDAOImpl implements PersonaDAO {

	private Connection connection;

	// Constructor que recibe una conexión
	public PersonaDAOImpl(Connection connection) {
		this.connection = connection;
	}


	// Método para insertar una nueva persona en la base de datos
	@Override
	public boolean crearPersona(Persona persona) {
		// Consulta SQL para insertar una persona en la base de datos
		String sql = "INSERT INTO Persona (nombre, email) VALUES (?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Establecemos los valores de los parámetros con los datos de la persona
			preparedStatement.setString(1, persona.getNombre());
			preparedStatement.setString(2, persona.getEmail());

			int rowsInserted = preparedStatement.executeUpdate();
			return rowsInserted > 0;
		} catch (SQLException e) {
			// Si ocurre un error durante la inserción, se captura y se imprime el mensaje de error
			System.out.println("Error al crear persona: " + e.getMessage());
		}
		return false;
	}

	// Método para obtener todas las personas de la base de datos y sus credenciales
	@Override
	public List<Persona> findAll() {
		List<Persona> personas = new ArrayList<>();
		// Consulta SQL para obtener todas las personas junto con sus credenciales (si existen)
		String sql = "SELECT p.id, p.nombre, p.email, c.usuario, c.password " +
				"FROM Persona p " +
				"LEFT JOIN Credenciales c ON p.id = c.id";

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			// Iteramos sobre los resultados de la consulta
			while (resultSet.next()) {
				// Obtenemos los datos de la persona y sus credenciales
				long id = resultSet.getLong("id");
				String nombre = resultSet.getString("nombre");
				String email = resultSet.getString("email");
				String usuario = resultSet.getString("usuario");
				String password = resultSet.getString("password");

				// Creamos el objeto Credenciales con los datos obtenidos
				Credenciales credenciales = new Credenciales(id, usuario, password);
				// Creamos el objeto Persona con los datos obtenidos y las credenciales
				Persona persona = new Persona(id, nombre, email, credenciales);
				// Añadimos la persona a la lista
				personas.add(persona);
			}
		} catch (SQLException e) {
			// Si ocurre un error durante la consulta, se captura y se imprime el mensaje de error
			System.out.println("Error al obtener personas: " + e.getMessage());
		}
		return personas;
	}


	// Método para obtener una persona por su ID
	@Override
	public Persona findById(Long id) {
		// Consulta SQL para obtener una persona y sus credenciales por su ID
		String sql = "SELECT p.id, p.nombre, p.email, c.usuario, c.password " +
				"FROM Persona p " +
				"LEFT JOIN Credenciales c ON p.id = c.id " +
				"WHERE p.id = ?";
		Persona persona = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			// Establecemos el valor del parámetro con el ID de la persona
			preparedStatement.setLong(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Si encontramos la persona con ese ID
				if (resultSet.next()) {
					long personaId = resultSet.getLong("id");
					String nombre = resultSet.getString("nombre");
					String email = resultSet.getString("email");
					String usuario = resultSet.getString("usuario");
					String password = resultSet.getString("password");

					// Creamos el objeto Credenciales con los datos obtenidos
					Credenciales credenciales = new Credenciales(personaId, usuario, password);
					// Creamos el objeto Persona con los datos obtenidos y las credenciales
					persona = new Persona(personaId, nombre, email, credenciales);
				}
			}
		} catch (SQLException e) {
			// Si ocurre un error durante la consulta, se captura y se imprime el mensaje de error
			System.out.println("Error al obtener persona por ID: " + e.getMessage());
		}
		return persona;
	}

}

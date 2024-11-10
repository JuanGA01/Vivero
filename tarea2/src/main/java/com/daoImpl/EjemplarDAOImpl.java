package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.dao.EjemplarDAO;
import com.model.Ejemplar;
import com.model.Planta;

public class EjemplarDAOImpl implements EjemplarDAO {

	private Connection connection;

	// Constructor para inyectar la conexión
	public EjemplarDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Ejemplar registrarEjemplar(Planta planta) {
		// Consulta SQL para insertar un nuevo ejemplar en la base de datos
		String sql = "INSERT INTO Ejemplar (codigo_planta) VALUES (?)";
		Ejemplar nuevoEjemplar = null;

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			// Se establece el valor del parámetro con el código de la planta
			preparedStatement.setString(1, planta.getCodigo());
			int rowsInserted = preparedStatement.executeUpdate();

			// Si se insertó el ejemplar correctamente, obtenemos la clave generada
			if (rowsInserted > 0) {
				try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						Long id = generatedKeys.getLong(1);

						// Creamos el nombre del ejemplar concatenando el código de la planta y el ID generado
						String nombreGenerado = planta.getCodigo() + "_" + id;

						// Creamos el objeto Ejemplar con el ID y nombre generados
						nuevoEjemplar = new Ejemplar(id, nombreGenerado);
					}
				}
			}
		} catch (SQLException e) {
			// Si ocurre un error durante la inserción, se captura y se imprime el mensaje de error
			System.out.println("Error al registrar el ejemplar: " + e.getMessage());
		}
		return nuevoEjemplar;
	}

	// Método para obtener una lista con los nombres de todos los ejemplares
	@Override
	public List<String> Ejemplares() {
		List<String> ejemplares = new ArrayList<>();
		String sql = "SELECT nombre FROM Ejemplar";

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			// Iteramos sobre los resultados y añadimos los nombres a la lista
			while (resultSet.next()) {
				String nombre = resultSet.getString("nombre");
				ejemplares.add(nombre);
			}
		} catch (SQLException e) {
			// Si ocurre un error durante la consulta, se captura y se imprime el mensaje de error
			System.out.println("Error al obtener los ejemplares: " + e.getMessage());
		}

		// Retornamos la lista de nombres de ejemplares
		return ejemplares;
	}

	// Método para obtener todos los ejemplares asociados a una planta
	public List<Ejemplar> obtenerEjemplaresPorPlanta(Planta planta) {
		List<Ejemplar> ejemplares = new ArrayList<>();
		String sql = "SELECT id, nombre FROM Ejemplar WHERE codigo_planta = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			// Establecemos el parámetro con el código de la planta
			preparedStatement.setString(1, planta.getCodigo());
			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				// Iteramos sobre los resultados y creamos objetos Ejemplar para cada uno
				while (resultSet.next()) {
					Ejemplar ejemplar = new Ejemplar();
					ejemplar.setId(resultSet.getLong("id"));
					ejemplar.setNombre(resultSet.getString("nombre"));
					ejemplares.add(ejemplar);
				}
			}
		} catch (SQLException e) {
			// Si ocurre un error durante la consulta, se captura y se imprime el mensaje de error
			System.out.println("Error al obtener ejemplares: " + e.getMessage());
		}
		return ejemplares;
	}

	@Override
	public List<Ejemplar> findAll() {
		List<Ejemplar> ejemplares = new ArrayList<>();
		String sql = "SELECT id, nombre FROM Ejemplar";

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				Ejemplar ejemplar = new Ejemplar();
				ejemplar.setId(resultSet.getLong("id"));
				ejemplar.setNombre(resultSet.getString("nombre"));
				ejemplares.add(ejemplar);
			}
		} catch (SQLException e) {
			// Si ocurre un error durante la consulta, se captura y se imprime el mensaje de error
			System.out.println("Error al obtener los ejemplares: " + e.getMessage());
		}
		return ejemplares;
	}

}


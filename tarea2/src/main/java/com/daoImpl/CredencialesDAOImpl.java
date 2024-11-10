package com.daoImpl;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;
import javax.crypto.Cipher;
import com.dao.CredencialesDAO;
import com.model.Credenciales;
import javax.crypto.spec.SecretKeySpec;

public class CredencialesDAOImpl implements CredencialesDAO {

	private Connection connection;

	//Claves secreta y algoritmo
	private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final String SECRET_KEY = "1234567890123456";

	// Constructor que toma una conexión
	public CredencialesDAOImpl(Connection connection) {
		this.connection = connection;
	}

	// Método para cifrar una cadena de texto con AES
	private String cifrarAES(String input) {
		try {
			Key key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encrypted = cipher.doFinal(input.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			throw new RuntimeException("Error al cifrar el texto", e);
		}
	}

	// Método para descifrar una cadena de texto cifrada con AES
	private String descifrarAES(String input) {
		try {
			Key key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decoded = Base64.getDecoder().decode(input);
			byte[] decrypted = cipher.doFinal(decoded);
			return new String(decrypted);
		} catch (Exception e) {
			throw new RuntimeException("Error al descifrar el texto", e);
		}
	}

	// Método para devolver las Credenciales
	@Override
	public Optional<Credenciales> findByUsuario(String usuario) {
		String sql = "SELECT id, usuario, password FROM Credenciales WHERE usuario = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, cifrarAES(usuario));

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					// Extraer y descifrar datos de la consulta
					long id = resultSet.getLong("id");
					String usuarioR = descifrarAES(resultSet.getString("usuario"));
					String password = descifrarAES(resultSet.getString("password"));

					// Crear objeto Credenciales y devolver Optional
					Credenciales credenciales = new Credenciales(id, usuarioR, password);
					return Optional.of(credenciales);
				}
			}
		} catch (SQLException e) {
			System.err.print("Error al buscar el usuario: " + e.getMessage());
		}
		// Si no se encuentra el usuario, se retorna un Optional vacío
		return Optional.empty();
	}

	// Implementación del método para verificar si las credenciales son correctas
	@Override
	public boolean autenticarUsuario(String usuario, String password) {
		String sql = "SELECT COUNT(*) FROM Credenciales WHERE usuario = ? AND password = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			// Cifra tanto el usuario como la contraseña antes de la consulta
			preparedStatement.setString(1, cifrarAES(usuario));
			preparedStatement.setString(2, cifrarAES(password));

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					return count > 0; // Si devuelve más de 0, entonces las credenciales son válidas
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al autenticar al usuario " + e.getMessage());
		}
		return false; // Si ocurre un error o no se encuentra el usuario, retorna false
	}

	// Implementación del método para crear un usuario
	@Override
	public boolean crearUsuario(Long idPersona, String usuario, String password) {
		String insertUserSql = "INSERT INTO Credenciales (id, usuario, password) VALUES (?, ?, ?)";

		try (PreparedStatement insertStatement = connection.prepareStatement(insertUserSql)) {
			insertStatement.setLong(1, idPersona);
			// Cifra tanto el usuario como la contraseña antes de almacenarlos en la base de datos
			insertStatement.setString(2, cifrarAES(usuario));
			insertStatement.setString(3, cifrarAES(password));

			int rowsInserted = insertStatement.executeUpdate();
			return rowsInserted > 0;
		} catch (SQLException e) {
			System.out.println("Error al crear el usuario: " + e.getMessage());
		}
		return false;
	}

	//Implementación del método para devolver las credencialesautenticadas
	@Override
	public Credenciales obtenerCredencialesAutenticadas(String usuario, String password) {
		// Primero, autenticamos al usuario
		if (autenticarUsuario(usuario, password)) {
			// Si la autenticación es exitosa, obtenemos las credenciales
			return findByUsuario(usuario).orElseThrow(() -> 
			new IllegalStateException("Las credenciales no fueron encontradas después de la autenticación.")
					);
		}
		// Si la autenticación falla, lanzamos una excepción
		throw new SecurityException("Autenticación fallida: usuario o contraseña incorrectos.");
	}
	
	
}
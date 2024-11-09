package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import com.dao.CredencialesDAO;
import com.model.Credenciales;

public class CredencialesDAOImpl implements CredencialesDAO {
	
    private Connection connection;
    
    // Constructor que toma una conexión
    public CredencialesDAOImpl(Connection connection) {
        this.connection = connection;
    }

    //Método para devolver las Credenciales
    @Override
    public Optional<Credenciales> findByUsuario(String usuario) {
        String sql = "SELECT id, usuario, password FROM Credenciales WHERE usuario = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Extraer datos de la consulta
                    long id = resultSet.getLong("id");
                    String usuarioR = resultSet.getString("usuario");
                    String password = resultSet.getString("password");

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
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, password);
            
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
            insertStatement.setString(2, usuario);
            insertStatement.setString(3, password);
            
            int rowsInserted = insertStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
        }
        return false;
    }
   
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

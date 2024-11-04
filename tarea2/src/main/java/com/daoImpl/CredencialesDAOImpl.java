package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.CredencialesDAO;

public class CredencialesDAOImpl implements CredencialesDAO {
	
    private Connection connection;

    // Constructor que toma una conexión
    public CredencialesDAOImpl(Connection connection) {
        this.connection = connection;
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
    
}

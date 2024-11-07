package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.dao.EjemplarDAO;
import com.model.Planta;

public class EjemplarDAOImpl implements EjemplarDAO {

    private Connection connection;

    // Constructor para inyectar la conexión
    public EjemplarDAOImpl(Connection connection) {
        this.connection = connection;
    }

    // Método para registrar un nuevo ejemplar
    @Override
    public boolean registrarEjemplar(Planta planta) {
        // Insertar solo el codigo_planta, ya que 'nombre' se generará automáticamente
        String sql = "INSERT INTO Ejemplar (codigo_planta) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, planta.getCodigo());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0; // Retorna true si la inserción fue exitosa
        } catch (SQLException e) {
            System.out.println("Error al registrar el ejemplar: " + e.getMessage());
        }
        
        return false; // Retorna false si ocurre algún error
    }


    // Método para obtener una lista con los nombres de todos los ejemplares
    @Override
    public List<String> Ejemplares() {
        List<String> ejemplares = new ArrayList<>();
        String sql = "SELECT nombre FROM Ejemplar";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                ejemplares.add(nombre);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los ejemplares: " + e.getMessage());
        }

        return ejemplares; // Retorna la lista de nombres de ejemplares
    }
	
}

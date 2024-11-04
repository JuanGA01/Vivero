package com.daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.dao.PlantaDAO;
import com.model.Planta;

public class PlantaDAOImpl implements PlantaDAO {
    private Connection connection;

    // Constructor que toma una conexión
    public PlantaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    // Método para listar todas las plantas
    @Override
    public List<Planta> listarPlantas() {
        List<Planta> plantas = new ArrayList<>();
        String sql = "SELECT codigo, nombrecomun, nombrecientifico FROM Planta";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String codigo = resultSet.getString("codigo");
                String nombreComun = resultSet.getString("nombrecomun");
                String nombreCientifico = resultSet.getString("nombrecientifico");
                Planta planta = new Planta(codigo, nombreComun, nombreCientifico);
                plantas.add(planta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plantas;
    }

    // Método para modificar una planta existente
    @Override
    public boolean modificarPlanta(Planta planta) {
        String sql = "UPDATE Planta SET nombrecomun = ?, nombrecientifico = ? WHERE codigo = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, planta.getNombreComun());
            preparedStatement.setString(2, planta.getNombreCientifico());
            preparedStatement.setString(3, planta.getCodigo());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0; // true si al menos una fila fue actualizada
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para crear una nueva planta, validando que el código sea único
    @Override
    public boolean crearPlanta(Planta planta) {
        // Primero verificamos si el código ya existe
        String checkSql = "SELECT COUNT(*) FROM Planta WHERE codigo = ?";
        String insertSql = "INSERT INTO Planta (codigo, nombrecomun, nombrecientifico) VALUES (?, ?, ?)";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
            checkStatement.setString(1, planta.getCodigo());
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    // Si el código ya existe, retornamos false
                    System.out.println("El código de la planta ya existe.");
                    return false;
                }
            }

            // Si no existe, procedemos a insertar la nueva planta
            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                insertStatement.setString(1, planta.getCodigo());
                insertStatement.setString(2, planta.getNombreComun());
                insertStatement.setString(3, planta.getNombreCientifico());

                int rowsInserted = insertStatement.executeUpdate();
                return rowsInserted > 0; // true si al menos una fila fue insertada
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

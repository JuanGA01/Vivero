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

    @Override
    public List<Planta> findAll() {
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

	@Override
	public void insert(Planta planta) throws SQLException {
	    String sql = "INSERT INTO Planta (codigo, nombrecomun, nombrecientifico) VALUES (?, ?, ?)";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setString(1, planta.getCodigo());
	        preparedStatement.setString(2, planta.getNombreComun());
	        preparedStatement.setString(3, planta.getNombreCientifico());
	        preparedStatement.executeUpdate();
	    }
	}

	@Override
	public Planta findById(String codigo) throws SQLException {
	    String sql = "SELECT codigo, nombrecomun, nombrecientifico FROM Planta WHERE codigo = ?";
	    Planta planta = null;
	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setString(1, codigo);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                String nombreComun = resultSet.getString("nombrecomun");
	                String nombreCientifico = resultSet.getString("nombrecientifico");
	                planta = new Planta(codigo, nombreComun, nombreCientifico);
	            }
	        }
	    }
	    return planta;
	}

	@Override
	public void update(Planta planta) throws SQLException {
	    String sql = "UPDATE Planta SET nombrecomun = ?, nombrecientifico = ? WHERE codigo = ?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setString(1, planta.getNombreComun());
	        preparedStatement.setString(2, planta.getNombreCientifico());
	        preparedStatement.setString(3, planta.getCodigo());
	        preparedStatement.executeUpdate();
	    }
	}

	@Override
	public void delete(String codigo) throws SQLException {
	    String sql = "DELETE FROM Planta WHERE codigo = ?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setString(1, codigo);
	        preparedStatement.executeUpdate();
	    }
	}

    
}

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
        String sql = "INSERT INTO Persona (nombre, email) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setString(2, persona.getEmail());
            
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear persona: " + e.getMessage());
        }
        return false; // Retorna false si ocurre algún error
    }

    // Método para obtener todas las personas de la base de datos y sus credenciales
    @Override
    public List<Persona> findAll() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT p.id, p.nombre, p.email, c.usuario, c.password " +
                     "FROM Persona p " +
                     "LEFT JOIN Credenciales c ON p.id = c.id";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                String usuario = resultSet.getString("usuario");
                String password = resultSet.getString("password");

                // Crear objeto Credenciales y asignarlo a Persona
                Credenciales credenciales = new Credenciales(id, usuario, password);
                Persona persona = new Persona(id, nombre, email, credenciales);
                personas.add(persona);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener personas: " + e.getMessage());
        }
        return personas;
    }


    @Override
    public Persona findById(Long id) {
        String sql = "SELECT p.id, p.nombre, p.email, c.usuario, c.password " +
                     "FROM Persona p " +
                     "LEFT JOIN Credenciales c ON p.id = c.id " +
                     "WHERE p.id = ?";
        Persona persona = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);  // Usamos 'id' para filtrar por la persona

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long personaId = resultSet.getLong("id");
                    String nombre = resultSet.getString("nombre");
                    String email = resultSet.getString("email");
                    String usuario = resultSet.getString("usuario");
                    String password = resultSet.getString("password");

                    // Crear objeto Credenciales y asignarlo a Persona
                    Credenciales credenciales = new Credenciales(personaId, usuario, password);
                    persona = new Persona(personaId, nombre, email, credenciales);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener persona por ID: " + e.getMessage());
        }
        return persona;
    }

    
}

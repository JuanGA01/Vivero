package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import com.dao.MensajeDAO;
import com.model.Ejemplar;
import com.model.Mensaje;
import com.model.Persona;
import com.model.Planta;

public class MensajeDAOImpl implements MensajeDAO {
    private Connection connection;

    public MensajeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo mensaje
    @Override
    public boolean insertarMensaje(Mensaje mensaje, Ejemplar ejemplar, Persona persona) {
        String sql = "INSERT INTO Mensaje (fechahora, mensaje, id_ejemplar, id_persona) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(mensaje.getFechahora()));
            preparedStatement.setString(2, mensaje.getMensaje());
            preparedStatement.setLong(3, ejemplar.getId()); // Obtenemos el ID del ejemplar
            preparedStatement.setLong(4, persona.getId()); // Obtenemos el ID de la persona

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar el mensaje: " + e.getMessage());
        }

        return false;
    }

    // Método para obtener un mensaje por su id
    @Override
    public Mensaje obtenerMensajePorId(Long id) {
        String sql = "SELECT id, fechahora, mensaje FROM Mensaje WHERE id = ?";
        Mensaje mensaje = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    mensaje = new Mensaje(
                        resultSet.getLong("id"),
                        resultSet.getTimestamp("fechahora").toLocalDateTime(), // Convertimos Timestamp a LocalDateTime
                        resultSet.getString("mensaje")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el mensaje: " + e.getMessage());
        }

        return mensaje;
    }

    // Método para obtener todos los mensajes
    @Override
    public List<Mensaje> obtenerTodosLosMensajes() {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT id, fechahora, mensaje FROM Mensaje";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Mensaje mensaje = new Mensaje(
                    resultSet.getLong("id"),
                    resultSet.getTimestamp("fechahora").toLocalDateTime(),
                    resultSet.getString("mensaje")
                );
                mensajes.add(mensaje);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los mensajes: " + e.getMessage());
        }

        return mensajes;
    }

    // Método para actualizar un mensaje existente
    @Override
    public boolean actualizarMensaje(Mensaje mensaje) {
        String sql = "UPDATE Mensaje SET fechahora = ?, mensaje = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(mensaje.getFechahora()));
            preparedStatement.setString(2, mensaje.getMensaje());
            preparedStatement.setLong(3, mensaje.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el mensaje: " + e.getMessage());
        }

        return false;
    }

    // Método para eliminar un mensaje por su id
    @Override
    public boolean eliminarMensaje(Long id) {
        String sql = "DELETE FROM Mensaje WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el mensaje: " + e.getMessage());
        }

        return false;
    }

    @Override
    public TreeMap<Mensaje, Persona> buscarMensajeXEjemplar(Long ejemplarId) {
        // Usamos un TreeMap para que los mensajes se ordenen por la fecha (fechahora)
        TreeMap<Mensaje, Persona> mensajesConPersonas = new TreeMap<>(
            Comparator.comparing(Mensaje::getFechahora) // Ordena los mensajes por su fecha de creación
        );

        String sql = "SELECT m.id AS mensaje_id, m.fechahora, m.mensaje, p.id AS persona_id, p.nombre, p.email " +
                     "FROM Mensaje m " +
                     "JOIN Persona p ON m.id_persona = p.id " +
                     "WHERE m.id_ejemplar = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Establecer el parámetro en la consulta
            preparedStatement.setLong(1, ejemplarId);

            // Ejecutar la consulta
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Iteramos sobre los resultados de la consulta
                while (resultSet.next()) {
                    // Crear el objeto Mensaje
                    Mensaje mensaje = new Mensaje();
                    mensaje.setId(resultSet.getLong("mensaje_id"));
                    mensaje.setFechahora(resultSet.getTimestamp("fechahora").toLocalDateTime());
                    mensaje.setMensaje(resultSet.getString("mensaje"));

                    // Crear el objeto Persona
                    Persona persona = new Persona();
                    persona.setId(resultSet.getLong("persona_id"));
                    persona.setNombre(resultSet.getString("nombre"));
                    persona.setEmail(resultSet.getString("email"));

                    // Asociar el mensaje con la persona en el TreeMap (se ordena por fecha automáticamente)
                    mensajesConPersonas.put(mensaje, persona);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar los mensajes por ejemplar: " + e.getMessage());
        }

        return mensajesConPersonas; // Retornamos el TreeMap de mensajes con sus respectivas personas, ordenados por fecha
    }

    @Override
    public List<Mensaje> obtenerMensajesPorPersona(Persona persona) {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT id, fechahora, mensaje FROM Mensaje WHERE id_persona = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, persona.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Mensaje mensaje = new Mensaje(
                        resultSet.getLong("id"),
                        resultSet.getTimestamp("fechahora").toLocalDateTime(),
                        resultSet.getString("mensaje")
                    );
                    mensajes.add(mensaje);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los mensajes por persona: " + e.getMessage());
        }

        return mensajes;
    }

    @Override
    public List<Mensaje> obtenerMensajesPorRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT id, fechahora, mensaje FROM Mensaje WHERE fechahora BETWEEN ? AND ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(desde));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(hasta));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Mensaje mensaje = new Mensaje(
                        resultSet.getLong("id"),
                        resultSet.getTimestamp("fechahora").toLocalDateTime(),
                        resultSet.getString("mensaje")
                    );
                    mensajes.add(mensaje);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los mensajes por rango de fechas: " + e.getMessage());
        }

        return mensajes;
    }

    @Override
    public List<Mensaje> obtenerMensajesPorTipoCodigoPlanta(Planta planta) {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT m.id, m.fechahora, m.mensaje " +
                     "FROM Mensaje m " +
                     "JOIN Ejemplar e ON m.id_ejemplar = e.id " +
                     "JOIN Planta p ON e.codigo_planta = p.codigo " +  // Usamos `codigo` en vez de `tipo`
                     "WHERE p.codigo = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, planta.getCodigo());  // Usamos `getCodigo()` en vez de `getTipo`

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Mensaje mensaje = new Mensaje(
                        resultSet.getLong("id"),
                        resultSet.getTimestamp("fechahora").toLocalDateTime(),
                        resultSet.getString("mensaje")
                    );
                    mensajes.add(mensaje);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener mensajes por código de planta: " + e.getMessage());
        }

        return mensajes;
    }

}
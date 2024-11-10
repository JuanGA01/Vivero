package com.dao;

import java.sql.SQLException;
import java.util.List;
import com.model.Planta;

public interface PlantaDAO {

    // Inserta una nueva planta en la base de datos
    boolean insert(Planta planta);

    // Busca una planta por su código
    Planta findById(String codigo) throws SQLException;

    // Obtiene todas las plantas de la base de datos
    List<Planta> findAll() throws SQLException;

    // Actualiza los datos de una planta
    boolean update(Planta planta);

    // Elimina una planta por su código
    boolean delete(String codigo);
    
}

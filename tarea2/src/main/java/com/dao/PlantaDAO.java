package com.dao;

import java.sql.SQLException;
import java.util.List;
import com.model.Planta;

public interface PlantaDAO {
    boolean insert(Planta planta);
    Planta findById(String codigo) throws SQLException;
    List<Planta> findAll() throws SQLException;
    boolean update(Planta planta);
    boolean delete(String codigo);
}

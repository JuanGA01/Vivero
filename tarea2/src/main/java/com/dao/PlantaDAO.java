package com.dao;

import java.sql.SQLException;
import java.util.List;
import com.model.Planta;

public interface PlantaDAO {
	
    void insert(Planta planta) throws SQLException;
    Planta findById(String codigo) throws SQLException;
    List<Planta> findAll() throws SQLException;
    void update(Planta planta) throws SQLException;
    void delete(String codigo) throws SQLException;
    
}

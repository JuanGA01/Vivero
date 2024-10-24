package DAO;

import java.util.List;
import modelo.Planta;

public interface PlantaDAO {
    // Método para listar todas las plantas
    List<Planta> listarPlantas();

    // Método para modificar una planta existente
    boolean modificarPlanta(Planta planta);

    // Método para crear una nueva planta, validando que el código sea único
    boolean crearPlanta(Planta planta);
}

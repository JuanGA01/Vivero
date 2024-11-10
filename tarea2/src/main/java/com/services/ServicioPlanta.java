package com.services;

import com.model.Planta;

public interface ServicioPlanta {

    // Devuelve una lista de todas las plantas en formato String.
    public String listaPlantas();

    // Inserta una nueva planta y devuelve un mensaje de confirmación.
    public String InsertarPlanta(Planta planta);

    // Verifica si una planta existe en el sistema.
    public boolean existePlanta(Planta planta);

    // Busca una planta por su ID y devuelve el objeto Planta correspondiente.
    Planta BuscarPlantaXId(Planta planta);

}

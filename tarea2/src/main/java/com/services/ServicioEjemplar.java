package com.services;

import java.util.Set;
import com.model.Persona;
import com.model.Planta;

public interface ServicioEjemplar {
    
    // Registra un nuevo ejemplar asociado a una planta y una persona.
    boolean registrarNuevoEjemplar(Planta planta, Persona persona);
    
    // Lista los ejemplares que pertenecen a los tipos de planta proporcionados.
    String listarEjemplaresPorTipoDePlanta(Set<Planta> listaABuscar);
    
    // Lista todos los ejemplares disponibles.
    String listarEjemplares();
    
    // Verifica si un ejemplar con el ID proporcionado existe.
    boolean existeEjemplar(Long id);
    
}


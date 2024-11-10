package servicesImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.daoImpl.EjemplarDAOImpl;
import com.daoImpl.MensajeDAOImpl;
import com.model.Ejemplar;
import com.model.Mensaje;
import com.model.Persona;
import com.model.Planta;
import com.services.ServicioEjemplar;
import com.utilities.MySqlDAOFactory;

public class ServicioEjemplarImpl implements ServicioEjemplar {

	private MySqlDAOFactory factoria;
	private EjemplarDAOImpl ejemplarDAO;
	private MensajeDAOImpl mensajeDAO;
	
	public ServicioEjemplarImpl() {
	    factoria = MySqlDAOFactory.getCon();
	    ejemplarDAO = (EjemplarDAOImpl) factoria.getEjemplaresDAO();
	    mensajeDAO = (MensajeDAOImpl) factoria.getMensajeDAO();
	}

    @Override
    public boolean registrarNuevoEjemplar(Planta planta, Persona persona) {
    	Mensaje mensaje = new Mensaje();
    	mensaje.setFechahora(LocalDateTime.now());
    	Ejemplar ejemplar = ejemplarDAO.registrarEjemplar(planta);
    	mensaje.setMensaje(persona.getNombre() + " creo un nuevo ejemplar en la fecha: " + mensaje.getFechahora().toString());
        return mensajeDAO.insertarMensaje( mensaje, ejemplar, persona);

    }

    @Override
    public String listarEjemplaresPorTipoDePlanta(Set<Planta> listaABuscar) {
        StringBuilder ejemplaresFiltrados = new StringBuilder(); // Usamos StringBuilder para optimizar la concatenación

        // Iterar sobre la lista de plantas
        for (Planta planta : listaABuscar) {
            // Obtener todos los ejemplares asociados a esta planta
            List<Ejemplar> ejemplares = ejemplarDAO.obtenerEjemplaresPorPlanta(planta); // Suponemos que este método existe y devuelve los ejemplares de la planta

            // Iterar sobre los ejemplares
            for (Ejemplar ejemplar : ejemplares) {
                // Verificar si el nombre del ejemplar comienza con el código de la planta
                if (ejemplar.getNombre().startsWith(planta.getCodigo())) {
                    // Agregar los datos del ejemplar a la cadena de resultados
                    ejemplaresFiltrados.append("ID: ").append(ejemplar.getId())
                                        .append(", Nombre: ").append(ejemplar.getNombre())
                                        .append("\n");
                }
            }
        }
        
        return ejemplaresFiltrados.toString(); // Devolver la cadena construida
    }

    @Override
    public String listarEjemplares() {
        List<Ejemplar> ejemplares = ejemplarDAO.findAll();
        StringBuilder resultado = new StringBuilder();
        
        if (ejemplares.isEmpty()) {
            return "No hay ejemplares registrados.";
        }
        
        for (Ejemplar ejemplar : ejemplares) {
            // Agregamos la información de cada ejemplar al StringBuilder
            resultado.append("ID: ").append(ejemplar.getId())
                     .append(", Nombre: ").append(ejemplar.getNombre())
                     .append("\n");
        }
        
        return resultado.toString();
    }

    @Override
    public boolean existeEjemplar(Long id) {
        List<Ejemplar> ejemplares = ejemplarDAO.findAll();

        for (Ejemplar ejemplar : ejemplares) {
            if (ejemplar.getId().equals(id)) {
                return true;  
            }
        }
        
        return false;
    }



}

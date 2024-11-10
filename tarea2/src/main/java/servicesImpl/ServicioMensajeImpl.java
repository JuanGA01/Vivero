package servicesImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.daoImpl.MensajeDAOImpl;
import com.model.Ejemplar;
import com.model.Mensaje;
import com.model.Persona;
import com.model.Planta;
import com.services.ServicioMensaje;
import com.utilities.MySqlDAOFactory;

public class ServicioMensajeImpl implements ServicioMensaje {
	
	private MySqlDAOFactory factoria;
	private MensajeDAOImpl mensajeDAO;
	
	public ServicioMensajeImpl() {
	    factoria = MySqlDAOFactory.getCon();
	    mensajeDAO= (MensajeDAOImpl) factoria.getMensajeDAO();
	}

	@Override
	public String crearMensaje(Persona persona, Ejemplar ejemplar, Mensaje mensaje) {
		if (mensajeDAO.insertarMensaje(mensaje, ejemplar, persona)) {
			return "Mensaje guardado";
		}else {
			return "A habido un error al guardar el mensaje";
		}
	}

	@Override
	public Mensaje VerMensajesDeSeguimientoEjemplar(Ejemplar ejemplar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buscarMensajeXEjemplar(Long id) {
		StringBuilder MensajeEjemplares = new StringBuilder();

		TreeMap<Mensaje, Persona> mensajesConPersonas = mensajeDAO.buscarMensajeXEjemplar(id);

		for (Map.Entry<Mensaje, Persona> entry : mensajesConPersonas.entrySet()) {

		    Mensaje mensaje = entry.getKey();
		    Persona persona = entry.getValue();

		    MensajeEjemplares.append("Datos del mensaje: \n");
		    MensajeEjemplares.append("ID: ").append(mensaje.getId()).append("\n");
		    MensajeEjemplares.append("Fecha y Hora: ").append(mensaje.getFechahora()).append("\n");
		    MensajeEjemplares.append("Mensaje: ").append(mensaje.getMensaje()).append("\n");
		    MensajeEjemplares.append("------------------------------------------------------\n");

		    MensajeEjemplares.append("Datos de la persona: \n");
		    MensajeEjemplares.append("ID: ").append(persona.getId()).append("\n");
		    MensajeEjemplares.append("Nombre: ").append(persona.getNombre()).append("\n");
		    MensajeEjemplares.append("Email: ").append(persona.getEmail()).append("\n");
		    MensajeEjemplares.append("------------------------------------------------------\n");
		    MensajeEjemplares.append("------------------------------------------------------\n");
		}

		String result = MensajeEjemplares.toString();
		return result;

	}

	@Override
	public String listarTodosMensajes() {
	    List<Mensaje> mensajes = mensajeDAO.obtenerTodosLosMensajes();
	    StringBuilder sb = new StringBuilder();

	    for (Mensaje mensaje : mensajes) {
	        sb.append("ID: ").append(mensaje.getId()).append("\n");
	        sb.append("Fecha y Hora: ").append(mensaje.getFechahora()).append("\n");
	        sb.append("Mensaje: ").append(mensaje.getMensaje()).append("\n");
	        sb.append("------------------------------------------------------\n");
	    }

	    return sb.toString();
	}

	@Override
	public String listarXPersona(Persona persona) {
	    List<Mensaje> mensajes = mensajeDAO.obtenerMensajesPorPersona(persona);
	    StringBuilder sb = new StringBuilder();

	    for (Mensaje mensaje : mensajes) {
	        sb.append("ID: ").append(mensaje.getId()).append("\n");
	        sb.append("Fecha y Hora: ").append(mensaje.getFechahora()).append("\n");
	        sb.append("Mensaje: ").append(mensaje.getMensaje()).append("\n");
	        sb.append("------------------------------------------------------\n");
	    }

	    return sb.toString();
	}

	@Override
	public String listarXRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
	    List<Mensaje> mensajes = mensajeDAO.obtenerMensajesPorRangoFechas(desde, hasta);
	    StringBuilder sb = new StringBuilder();

	    for (Mensaje mensaje : mensajes) {
	        sb.append("ID: ").append(mensaje.getId()).append("\n");
	        sb.append("Fecha y Hora: ").append(mensaje.getFechahora()).append("\n");
	        sb.append("Mensaje: ").append(mensaje.getMensaje()).append("\n");
	        sb.append("------------------------------------------------------\n");
	    }

	    return sb.toString();
	}

	@Override
	public String listarXTipoPlanta(Planta planta) {
	    List<Mensaje> mensajes = mensajeDAO.obtenerMensajesPorTipoCodigoPlanta(planta);
	    StringBuilder resultado = new StringBuilder();

	    for (Mensaje mensaje : mensajes) {
	        resultado.append("ID: ").append(mensaje.getId()).append("\n")
	                 .append("Fecha y Hora: ").append(mensaje.getFechahora()).append("\n")
	                 .append("Mensaje: ").append(mensaje.getMensaje()).append("\n")
	                 .append("------------------------------------------------------\n");
	    }

	    return resultado.toString();
	}



}

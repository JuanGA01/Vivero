package servicesImpl;

import java.util.Map;
import java.util.TreeMap;
import com.daoImpl.MensajeDAOImpl;
import com.model.Credenciales;
import com.model.Ejemplar;
import com.model.Mensaje;
import com.model.Persona;
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
	public boolean crearMensaje(Credenciales credenciales, Ejemplar ejemplar, Mensaje mensaje) {
		// TODO Auto-generated method stub
		return false;
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


}

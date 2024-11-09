package servicesImpl;

import com.daoImpl.PersonaDAOImpl;
import com.model.Persona;
import com.services.ServicioPersona;
import com.utilities.MySqlDAOFactory;

public class ServicioPersonaImpl implements ServicioPersona {
	
	private MySqlDAOFactory factoria;
	private PersonaDAOImpl personaDao;
	
	public ServicioPersonaImpl() {
	    factoria = MySqlDAOFactory.getCon();
	    personaDao = (PersonaDAOImpl) factoria.getPersonaDAO();
	}

	@Override
	public Boolean CorreoExistente(Persona persona) {
		for(Persona p : personaDao.findAll()) {
			if (p.getEmail().equals(persona.getEmail())) {
				return true;
			}
		}
		return false;
	}


	@Override
	public Long GuardarPersona(Persona persona) {
	    // Verificar si el correo noexiste
	    if (!CorreoExistente(persona)) {
	        personaDao.crearPersona(persona);
	        // Buscar a la persona recién insertada por su correo para obtener su id
	        for (Persona p : personaDao.findAll()) {
	            if (p.getEmail().equals(persona.getEmail())) {
	                return p.getId();
	            }
	        }
	    } else {
	        System.out.println("El correo ya está registrado.");
	    }
	    return null; // Retorna null si la persona no se insertó o no se encontró
	}


}

package servicesImpl;

import java.util.Optional;
import com.daoImpl.CredencialesDAOImpl;
import com.model.Credenciales;
import com.services.ServicioCredenciales;
import com.utilities.MySqlDAOFactory;

public class ServicioCredencialesImpl implements ServicioCredenciales {
	
	private MySqlDAOFactory factoria;
	private CredencialesDAOImpl credencialesDao;
	
	public ServicioCredencialesImpl() {
	    factoria = MySqlDAOFactory.getCon();
	    credencialesDao = (CredencialesDAOImpl) factoria.getCredencialesDAO();
	}

	@Override
	public String loginUsuario(String usuario, String password) {
	    Optional<Credenciales> credencialesOpt = credencialesDao.findByUsuario(usuario);
	    
	    // Existe el usuario
	    if (!credencialesOpt.isPresent()) {
	        return "El usuario no existe";
	    }
	    
	    // Validate the password
	    Credenciales credenciales = credencialesOpt.get();
	    if (credenciales.getPassword().equals(password)) {
	        return "Bienvenido " + usuario;
	    } else {
	        return "Contraseña incorrecta";
	    }
	}

	@Override
	public Boolean UsuarioCorrecto(String usuario, String password) {
		Optional<Credenciales> credencialesOpt = credencialesDao.findByUsuario(usuario);
	    
	    // Existe el usuario
	    if (!credencialesOpt.isPresent()) {
	        return false;
	    }
	    
	    // Validate the password
	    Credenciales credenciales = credencialesOpt.get();
	    if (credenciales.getPassword().equals(password)) {
	        return true;
	    } else {
	        return false;
	    }
	}

	@Override
	public Boolean UsuarioExistente(String usuario) {
		Optional<Credenciales> credencialesOpt = credencialesDao.findByUsuario(usuario);
	    
	    // Existe el usuario
	    if (!credencialesOpt.isPresent()) {
	        return false;
	    }else {
	    	return true;
	    }
	}

	@Override
	public String GuardarCredenciales(Long idPersona, Credenciales credenciales) {
		if (!UsuarioExistente(credenciales.getUsuario())) {
			credencialesDao.crearUsuario(idPersona, credenciales.getUsuario(),credenciales.getPassword());
			return "Credenciales guardadas correctamente";
		}else {
			return "Error al guardar las credenciales";
		}
		
	}

	
}

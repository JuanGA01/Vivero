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
        // Buscar las credenciales del usuario en la base de datos
        Optional<Credenciales> credencialesOpt = credencialesDao.findByUsuario(usuario);
        
        // Si no existe el usuario, retornar un mensaje de error
        if (!credencialesOpt.isPresent()) {
            return "El usuario no existe";
        }
        
        // Verificar si la contraseña es correcta
        Credenciales credenciales = credencialesOpt.get();
        if (credenciales.getPassword().equals(password)) {
            return "Bienvenido " + usuario;
        } else {
            return "Contraseña incorrecta";
        }
        
    }

    @Override
    public Boolean UsuarioCorrecto(String usuario, String password) {
        // Buscar las credenciales del usuario en la base de datos
        Optional<Credenciales> credencialesOpt = credencialesDao.findByUsuario(usuario);
        
        // Si no existe el usuario, retornar false
        if (!credencialesOpt.isPresent()) {
            return false;
        }
        
        // Verificar si la contraseña es correcta
        Credenciales credenciales = credencialesOpt.get();
        return credenciales.getPassword().equals(password);
    }

    @Override
    public Boolean UsuarioExistente(String usuario) {
        // Buscar las credenciales del usuario
        Optional<Credenciales> credencialesOpt = credencialesDao.findByUsuario(usuario);
        
        // Si el usuario no existe, retorna false
        return credencialesOpt.isPresent();
    }
    

    @Override
    public String GuardarCredenciales(Long idPersona, Credenciales credenciales) {
        // Verificar si el usuario ya existe antes de guardarlo
        if (!UsuarioExistente(credenciales.getUsuario())) {
            credencialesDao.crearUsuario(idPersona, credenciales.getUsuario(), credenciales.getPassword());
            return "Credenciales guardadas correctamente";
        } else {
            return "Error al guardar las credenciales";
        }
        
    }
    
    
}

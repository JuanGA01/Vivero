package DAO;

public interface CredencialesDAO {
    // Método para verificar si un usuario y contraseña son válidos
    boolean autenticarUsuario(String usuario, String password);
}

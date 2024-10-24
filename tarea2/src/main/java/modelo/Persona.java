package modelo;

public class Persona {
	private Long id;
	private String nombre;
	private String email;
	private Credenciales crecenciales;
	
	public Persona() {
		super();
	}

	public Persona(Long id, String nombre, String email, Credenciales crecenciales) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.crecenciales = crecenciales;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Credenciales getCrecenciales() {
		return crecenciales;
	}

	public void setCrecenciales(Credenciales crecenciales) {
		this.crecenciales = crecenciales;
	}
	
}

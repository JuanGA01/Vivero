package modelo;

import java.time.LocalDateTime;

public class Mensaje {
	private Long id;
	private LocalDateTime fechahora;
	private String mensaje;
	
	public Mensaje() {
		super();
	}

	public Mensaje(Long id, LocalDateTime fechahora, String mensaje) {
		super();
		this.id = id;
		this.fechahora = fechahora;
		this.mensaje = mensaje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechahora() {
		return fechahora;
	}

	public void setFechahora(LocalDateTime fechahora) {
		this.fechahora = fechahora;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}

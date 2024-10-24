package modelo;

import java.util.List;

public class Planta {
	private String codigo;
	private String nombrecomun;
	private String nombrecientifico;
	private List<Ejemplar> Ejemplares;
	
	public Planta() {
		super();
	}
			
	public Planta(String codigo, String nombrecomun, String nombrecientifico, List<Ejemplar> ejemplares) {
		super();
		this.codigo = codigo;
		this.nombrecomun = nombrecomun;
		this.nombrecientifico = nombrecientifico;
		Ejemplares = ejemplares;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombrecomun() {
		return nombrecomun;
	}

	public void setNombrecomun(String nombrecomun) {
		this.nombrecomun = nombrecomun;
	}

	public String getNombrecientifico() {
		return nombrecientifico;
	}

	public void setNombrecientifico(String nombrecientifico) {
		this.nombrecientifico = nombrecientifico;
	}

	public List<Ejemplar> getEjemplares() {
		return Ejemplares;
	}

	public void setEjemplares(List<Ejemplar> ejemplares) {
		Ejemplares = ejemplares;
	}
	
}

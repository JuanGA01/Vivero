package com.model;

import java.util.Objects;

public class Planta implements Comparable<Planta> {
	private String codigo;
	private String nombreComun;
	private String nombreCientifico;
	
	public Planta() {
		super();
	}
	
	public Planta(String codigo, String nombreComun, String nombreCientifico) {
		super();
		this.codigo = codigo;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNombreComun() {
		return nombreComun;
	}
	
	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}
	
	public String getNombreCientifico() {
		return nombreCientifico;
	}
	
	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	@Override
	public int compareTo(Planta o) {
		if (this.nombreComun.compareTo(o.nombreComun) == 0)
	      {
	         return this.nombreComun.compareTo(o.nombreComun);
	      }
	      else return this.nombreComun.compareTo(o.nombreComun);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, nombreCientifico, nombreComun);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planta other = (Planta) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(nombreCientifico, other.nombreCientifico)
				&& Objects.equals(nombreComun, other.nombreComun);
	}
	
}

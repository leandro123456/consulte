package com.lgg.nticxs.web.model;

public class SimpleAlumno {
	private String nombre;
	private String materia;
	
	public SimpleAlumno(String nombre, String materia) {
		this.nombre= nombre;
		this.materia = materia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}
	
	
}

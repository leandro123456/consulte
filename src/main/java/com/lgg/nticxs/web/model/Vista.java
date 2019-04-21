package com.lgg.nticxs.web.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;


@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Vista {
	@Id
	@GeneratedValue
	@Field(name = "_id")
	private String id;
	
	@Field(name = "name")
	private String name;
	
	@Field(name = "inicio")
	private String inicio;
	
	@Field(name = "fin")
	private String fin;
	
	@Field (name = "contenido")
	private Map<String,String> contenido;

	public Vista() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public Map<String,String> getContenido() {
		if(this.contenido== null)
			this.contenido=new HashMap<>();
		return contenido;
	}

	public void setContenido(Map<String,String> contenido) {
		this.contenido = contenido;
	}
	
	

}

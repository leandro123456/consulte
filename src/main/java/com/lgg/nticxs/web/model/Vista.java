package com.lgg.nticxs.web.model;

import java.util.HashMap;
import java.util.Map;

import org.bson.codecs.pojo.annotations.BsonProperty;

import com.lgg.nticxs.web.DAO.Mongo.MongoDBObject;

public class Vista extends MongoDBObject {
	
	private static final long serialVersionUID = -4346222511562336633L;
	static final public String TEMP_RELOJ = "temperatura_reloj";
	static final public String TEMP_HORIZONTAL = "temperatura_horizontal";
	static final public String SONOFF = "sonoff";
	static final public String SONOFF_DOS = "sonofftwo";
	static final public String ALARMA = "alarma";
	
	@BsonProperty("id")
	private String id;
	
	private String name;
	private String inicio;
	private String fin;
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

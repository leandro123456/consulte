package com.lgg.nticxs.web.model;

import javax.persistence.Embeddable;


import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

	
	

@Embeddable
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Enseña {
	static final public String ACTIVIDADES = "actividad";
	static final public String TRABAJO_PRACTICO = "trabajo_practico";
	static final public String EVALUACION = "evaluacion";
	static final public Integer PRIMER_TRIMESTRE = 1;
	static final public Integer SEGUNDO_TRIMESTRE = 2;
	static final public Integer TERCER_TRIMESTRE = 3;
	
	
	@Field(name = "_id")
	private String id;

	@Field (name = "año")
	private String año;
	
	@Field (name = "iddocente")
	private String iddocente;
	
	@Field (name = "idalumno")
	private String idalumno;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getIddocente() {
		return iddocente;
	}

	public void setIddocente(String iddocente) {
		this.iddocente = iddocente;
	}

	public String getIdalumno() {
		return idalumno;
	}

	public void setIdalumno(String idalumno) {
		this.idalumno = idalumno;
	}

	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		this.año = año;
	}


	}

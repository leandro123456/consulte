package com.lgg.nticxs.web.model;

import javax.persistence.Embeddable;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

	
	
@Embeddable
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Nota {
	static final public String ACTIVIDADES = "actividad";
	static final public String TRABAJO_PRACTICO = "trabajo_practico";
	static final public String EVALUACION = "evaluacion";
	static final public String TRIMESTRAL = "trimestral";
	static final public Integer PRIMER_TRIMESTRE = 1;
	static final public Integer SEGUNDO_TRIMESTRE = 2;
	static final public Integer TERCER_TRIMESTRE = 3;
	
	@Field (name = "tipo")
	private String tipo;
	
	@Field (name = "fecha")
	private String fecha;
	
	@Field (name = "valor")
	private Double valor;
	
	@Field (name = "trimestre")
	private Integer trimestre;
	
	@Field (name = "descripcion")
	private String descripcion;
	
	@Field (name = "idmateria")
	private String idmateria;
	
	@Field (name = "idalumno")
	private String idalumno;
	
	public String getIdmateria() {
		return idmateria;
	}

	public void setIdmateria(String idmateria) {
		this.idmateria = idmateria;
	}

	public String getIdalumno() {
		return idalumno;
	}

	public void setIdalumno(String idalumno) {
		this.idalumno = idalumno;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	}

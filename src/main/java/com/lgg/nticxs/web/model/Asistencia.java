package com.lgg.nticxs.web.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Embeddable
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Asistencia {

	static final public Integer PRESENTE = 0;
	static final public Integer AUSENTE = 1;
	static final public Integer AUSENTE_JUSTIFICADO = 2;
		
	@Field (name = "tipo")
	private Integer tipo;
	
	@Field (name = "fecha")
	private String fecha;
	
	@Field (name = "descripcion")
	private String descripcion;
	
	@Field (name = "idmateria")
	private String idmateria;
	
	@Field (name = "idalumno")
	private String idalumno;
	
	@Field (name = "trimestre")
	private Integer trimestre;

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

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

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}	
}

package com.lgg.nticxs.web.model;

import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;


@Embeddable
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Mensaje {
	
	static final public String PRIORIDAD_BAJA = "baja";
	static final public String PRIORIDAD_MEDIA = "media";
	static final public String PRIORIDAD_ALTA = "alta";
	

	@Field (name = "tipo")
	private String tipo;
	
	@Field (name = "prioridad")
	private String prioridad;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Field (name = "contenido")
	private String contenido;
	
	@Field (name = "asunto")
	private String asunto;
	
	@Field (name = "copiapadre")
	private Boolean copiapadre;
	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Boolean getCopiapadre() {
		return copiapadre;
	}

	public void setCopiapadre(Boolean copiapadre) {
		this.copiapadre = copiapadre;
	}



	}

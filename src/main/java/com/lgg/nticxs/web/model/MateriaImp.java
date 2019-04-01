package com.lgg.nticxs.web.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class MateriaImp {
	static final public String ACTIVIDADES = "actividad";
	static final public String TRABAJO_PRACTICO = "trabajo_practico";
	static final public String EVALUACION = "evaluacion";
	static final public Integer PRIMER_TRIMESTRE = 1;
	static final public Integer SEGUNDO_TRIMESTRE = 2;
	static final public Integer TERCER_TRIMESTRE = 3;
	
	@Id
	@GeneratedValue
	@Field(name = "_id")
	private String id;
		
		@Field (name = "identifier")
		private String identifier;
		
		@Field (name = "anio")
		private Integer anio;
		
		@Field (name = "nombre")
		private String nombre;
		
		@Field (name = "docente")
		private Docente docente;
	
		@ElementCollection
		@Field (name = "notastrimestrales")
		private List<Nota> notastrimestrales;
		
		@ElementCollection
		@Field (name = "notasparciales")
		private List<Nota> notasparciales;
		
		
		@ElementCollection
		@Field (name = "asistencia")
		private List<Asistencia> asistencia;
		
		@ElementCollection
		@Field (name = "mensaje")
		private List<Mensaje> mensaje;
		

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getIdentifier() {
			return identifier;
		}

		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}

		public static String getActividades() {
			return ACTIVIDADES;
		}

		public static String getTrabajoPractico() {
			return TRABAJO_PRACTICO;
		}

		public static String getEvaluacion() {
			return EVALUACION;
		}

		public static Integer getPrimerTrimestre() {
			return PRIMER_TRIMESTRE;
		}

		public static Integer getSegundoTrimestre() {
			return SEGUNDO_TRIMESTRE;
		}

		public static Integer getTercerTrimestre() {
			return TERCER_TRIMESTRE;
		}

		public Integer getAnio() {
			return anio;
		}

		public void setAnio(Integer anio) {
			this.anio = anio;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Docente getDocente() {
			return docente;
		}

		public void setDocente(Docente docente) {
			this.docente = docente;
		}

		public List<Nota> getNotastrimestrales() {
			return notastrimestrales;
		}

		public void setNotastrimestrales(List<Nota> notastrimestrales) {
			this.notastrimestrales = notastrimestrales;
		}

		public List<Nota> getNotasparciales() {
			return notasparciales;
		}

		public void setNotasparciales(List<Nota> notasparciales) {
			this.notasparciales = notasparciales;
		}

		public List<Asistencia> getAsistencia() {
			return asistencia;
		}

		public void setAsistencia(List<Asistencia> asistencia) {
			this.asistencia = asistencia;
		}

		public List<Mensaje> getMensaje() {
			return mensaje;
		}

		public void setMensaje(List<Mensaje> mensaje) {
			this.mensaje = mensaje;
		}
		
	}


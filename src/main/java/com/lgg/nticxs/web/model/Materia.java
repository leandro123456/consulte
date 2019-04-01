package com.lgg.nticxs.web.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Embeddable
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Materia {
	static final public String ACTIVIDADES = "actividad";
	static final public String TRABAJO_PRACTICO = "trabajo_practico";
	static final public String EVALUACION = "evaluacion";
	static final public Integer PRIMER_TRIMESTRE = 1;
	static final public Integer SEGUNDO_TRIMESTRE = 2;
	static final public Integer TERCER_TRIMESTRE = 3;
	
	

	@ElementCollection
	@Field (name = "materia")
	private List<Materia.materia> materia;

	public List<Materia.materia> getMateria() {
		return materia;
	}

	public void setMateria(List<Materia.materia> materia) {
		this.materia = materia;
	}
	
	
	
	@Embeddable
	@NoSql(dataFormat=DataFormatType.MAPPED)
	public static class materia{
		@Field (name = "identifier")
		private String identifier;
		
		@Field (name = "name")
		private String name;
		
		
		@Field (name = "anio")
		private Integer anio;

		public String getIdentifier() {
			return identifier;
		}

		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}

		public Integer getAnio() {
			return anio;
		}

		public void setAnio(Integer anio) {
			this.anio = anio;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}

	}

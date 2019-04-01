package com.lgg.nticxs.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

import com.lgg.nticxs.web.DAO.AlumnoDAO;

@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Padre{

	@Id
	@GeneratedValue
	@Field(name = "_id")
	private String id;
	
	@Field (name = "name")
	private String name;
	
	@Field (name = "email")
	private String email;
	
	@Field (name = "password")
	private byte[] password;

	@Field (name = "delete")
	private Boolean delete;
	
	@ElementCollection
	@Field(name="alumno")
	private List<String> alumno;
	
	@Field (name = "role")
	private String role;
	
	public Padre(){
	}
	
	public Padre(ArrayList<String> alumnos){
		AlumnoDAO alumnodao = new AlumnoDAO();
		for(String alum: alumnos){
			Alumno estudiante = alumnodao.retrieveByName(alum);
			if(estudiante != null)
				alumno.add(estudiante.getName());
		}
	}

	public List<String> getAlumno() {
		return alumno;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAlumno(List<String> alumno) {
		this.alumno = alumno;
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

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}


	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
	
}

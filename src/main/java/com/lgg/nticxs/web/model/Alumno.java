package com.lgg.nticxs.web.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Alumno{
     static final public String ROLE_ALUMNO = "ALUMNO";

	@Id
	@GeneratedValue
	@Field(name = "_id")
	private String id;
	
	@Field (name = "name")
	private String name;
	
	@Field (name = "password")
	private byte[] password;

	@Field (name = "delete")
	private Boolean delete;
	
	@Field (name = "email")
	private String email;
	
	@Field (name = "cuenta_iniciada")
	private Boolean cuenta_iniciada;
	
	@Embedded
	@Field(name="ciclolectivo")
	private Ciclolectivo ciclolectivo;

	@Field (name = "role")
	private String role;
	
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Ciclolectivo getCiclolectivo() {
		return ciclolectivo;
	}


	public void setCiclolectivo(Ciclolectivo ciclolectivo) {
		this.ciclolectivo = ciclolectivo;
	}


	public Alumno(){
		this.setRole(ROLE_ALUMNO);
		cuenta_iniciada=false;
		this.setDelete(false);
	}
	
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
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

	public Boolean getCuenta_iniciada() {
		return cuenta_iniciada;
	}

	public void setCuenta_iniciada(Boolean cuenta_iniciada) {
		this.cuenta_iniciada = cuenta_iniciada;
	}	
}

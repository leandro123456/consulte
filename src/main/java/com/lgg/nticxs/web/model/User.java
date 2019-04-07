package com.lgg.nticxs.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class User{
     static final public String ROLE_SUPERADMIN = "SUPERADMIN";
     static final public String ROLE_ADMIN = "ADMIN";
     static final public String ROLE_USER = "USER";


	@Id
	@GeneratedValue
	@Field(name = "_id")
	private String id;
	
	@Field (name = "firstname")
	private String firstname;
	
	@Field (name = "lastname")
	private String lastname;
	
	@Field (name = "password")
	private byte[] password;

	@Field (name = "delete")
	private Boolean delete;
	
	@Field (name = "email")
	private String email;
	
	@Field (name = "cuenta_iniciada")
	private Boolean cuenta_iniciada;

	@Field (name = "role")
	private String role;
	
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public User(){
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


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
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

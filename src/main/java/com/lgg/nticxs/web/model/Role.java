package com.lgg.nticxs.web.model;

import com.lgg.nticxs.web.DAO.Mongo.MongoDBObject;

public class Role extends MongoDBObject{
	
	private static final long serialVersionUID = -4346222511562336633L;

	private String id;
	private String nameRole;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
}

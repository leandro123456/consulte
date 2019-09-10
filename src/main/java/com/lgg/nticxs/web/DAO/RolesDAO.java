package com.lgg.nticxs.web.DAO;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.conversions.Bson;

import com.lgg.nticxs.web.DAO.Mongo.MongoDBClient;
import com.lgg.nticxs.web.model.Role;

public class RolesDAO extends MongoDBClient<Role>{

	
	public RolesDAO() {
		super(Role.class);
	}

	public List<Role> retrieveAll() {
		return this.retrieveAll();
	}
	
	public Role retrieveByNameRole(String nameRole) {
		Bson filter = eq("nameRole", nameRole);
		return this.retrieveByFilter(filter);
	}

	@Override
	protected String getDatabaseName() {
		return "MQTT-Manager";
	}
}
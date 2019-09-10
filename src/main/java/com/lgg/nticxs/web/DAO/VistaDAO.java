package com.lgg.nticxs.web.DAO;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;
import org.bson.conversions.Bson;

import com.lgg.nticxs.web.DAO.Mongo.MongoDBClient;
import com.lgg.nticxs.web.dbcommands.MongoCommands;
import com.lgg.nticxs.web.model.Vista;

public class VistaDAO extends MongoDBClient<Vista>{
	static final private String COLLECTION= "VISTA";
	static final private String FIELD_NAME= "name";

	
	public VistaDAO() {
		super(Vista.class);
	}

	public List<Vista> retrieveAll() {
		return this.retrieveAll();
	}
	
	public Vista retrieveById(String vistaId) {
		Bson filter = eq("id", vistaId);
		return this.retrieveByFilter(filter);
	}
	
	public Vista retrieveByName(String name) {
		Bson filter = eq("name", name);
		return this.retrieveByFilter(filter);
	}
	
	public void deleteVista(Vista vista){
		this.delete(vista);
	}
	
    public void deleteByname(String name) {
		MongoCommands.Delete(COLLECTION, FIELD_NAME, name);
	}
    
	@Override
	protected String getDatabaseName() {
		return "MQTT-Manager";
	}
}

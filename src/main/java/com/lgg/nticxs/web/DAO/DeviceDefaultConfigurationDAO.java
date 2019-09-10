package com.lgg.nticxs.web.DAO;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.conversions.Bson;

import com.lgg.nticxs.web.DAO.Mongo.MongoDBClient;
import com.lgg.nticxs.web.model.DeviceDefaultConfiguration;

public class DeviceDefaultConfigurationDAO extends MongoDBClient<DeviceDefaultConfiguration>{
	
	public DeviceDefaultConfigurationDAO() {
		super(DeviceDefaultConfiguration.class);
	}
	
	public List<DeviceDefaultConfiguration> retrieveAll() {
		return this.retrieveAll();
	}
	
	public DeviceDefaultConfiguration retrieveById(String userId) {
		Bson filter = eq("id", userId);
		return this.retrieveByFilter(filter);
	}
	
	public DeviceDefaultConfiguration retrieveByName(String name) {
		Bson filter = eq("name", name);
		return this.retrieveByFilter(filter);
	}

	@Override
	protected String getDatabaseName() {
		return "MQTT-Manager";
	}

}

package com.lgg.nticxs.web.DAO;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.conversions.Bson;

import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.DAO.Mongo.MongoDBClient;;


public class DeviceDAO extends MongoDBClient<Device>{

	public DeviceDAO() {
		super(Device.class);
	}
	
	public List<Device> retrieveAllDevices() {
		return this.retrieveAll();
	}
	
	public Device retrieveById(String deviceId) {
		Bson filter = eq("id", deviceId);
		return this.retrieveByFilter(filter);
	}

	public Device retrieveAllByUser(String serialnumber) {
		Bson filter = eq("user", serialnumber);
		return this.retrieveByFilter(filter);
	}
	
	public Device retrieveAllByDevice(String device) {
		Bson filter = eq("user", device);
		return this.retrieveByFilter(filter);
	}
	
	@Override
	protected String getDatabaseName() {
		return "MQTT-Manager";
	}
	
	
}

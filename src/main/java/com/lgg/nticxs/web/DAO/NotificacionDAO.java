package com.lgg.nticxs.web.DAO;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.conversions.Bson;

import com.lgg.nticxs.web.DAO.Mongo.MongoDBClient;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.Notificacion;

public class NotificacionDAO extends MongoDBClient<Notificacion>{
	
	public NotificacionDAO(){
		super (Notificacion.class);
	}
	
	public List<Notificacion> retrieveAllNotificaciones() {
		return this.retrieveAll();
	}
	
	public Notificacion retrieveById(String NotificacionId) {
		Bson filter = eq("id", NotificacionId);
		return this.retrieveByFilter(filter);
	}

	public List<Notificacion> retrieveAllByUser(String user) {
		Bson filter = eq("serialnumber", user);
		return this.retrieveListByFilter(filter);
	}
	
	public List<Notificacion> retrieveAllByDevice(String serialnumber) {
		Bson filter = eq("dispositivos", serialnumber);
		return this.retrieveListByFilter(filter);
	}
	
	
	@Override
	protected String getDatabaseName() {
		return "MQTT-Manager";
	}

}

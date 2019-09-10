package com.lgg.nticxs.web.DAO;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.conversions.Bson;

import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.DAO.Mongo.*;


public class DeviceDAO extends MongoDBClient<Device>{

	public DeviceDAO() {
		super(Device.class);
	}
	
	public List<Device> retrieveAllDevices() {
		return this.retrieveAll();
	}
	
	public Device retrieveById(String deviceId) {
		Bson filter = eq("deviceId", deviceId);
		return this.retrieveListByFilter(filter);
	}

	
	@SuppressWarnings("unchecked")
	public Device retrieveBySerialNumber(String serialnumber) {
		String sql = "SELECT u FROM Device u WHERE u.serialnumber = :serialnumber and u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("serialnumber", serialnumber);
		List<Device> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
    public void deleteUserOwner(String userowner) {
		String sql = "SELECT u FROM Device u WHERE u.userowner = :userowner and u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("userowner", userowner);
		List<Device> list = query.getResultList();
		list.get(0).setDelete(true);
		update(list.get(0));
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.Device;
import com.lgg.nticxs.web.model.User;

public class DeviceDAO extends JPADAO<Device>{

	@SuppressWarnings("unchecked")
	public List<Device> retrieveAll() {
		String sql = "SELECT u FROM Device u WHERE u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		List<Device> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Device retrieveById(String userId) {
		String sql = "SELECT u FROM Device u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", userId);
		List<Device> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
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
	
	
}

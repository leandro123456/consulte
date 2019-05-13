package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.DeviceDefaultConfiguration;

public class DeviceDefaultConfigurationDAO extends JPADAO<DeviceDefaultConfiguration>{

	@SuppressWarnings("unchecked")
	public List<DeviceDefaultConfiguration> retrieveAll() {
		String sql = "SELECT u FROM Device u WHERE u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		List<DeviceDefaultConfiguration> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public DeviceDefaultConfiguration retrieveById(String userId) {
		String sql = "SELECT u FROM Device u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", userId);
		List<DeviceDefaultConfiguration> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public DeviceDefaultConfiguration retrieveByName(String name) {
		String sql = "SELECT u FROM Device u WHERE u.name = :name";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("name", name);
		List<DeviceDefaultConfiguration> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}

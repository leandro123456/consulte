package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.Device;

public class AdministrativoDAO extends JPADAO<Device>{


	@SuppressWarnings("unchecked")
	public List<Device> retrieveAll() {
		String sql = "SELECT u FROM Administrativo u WHERE u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		List<Device> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Device retrieveById(String userId) {
		String sql = "SELECT u FROM Administrativo u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", userId);
		List<Device> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Device retrieveByName(String name) {
		String sql = "SELECT u FROM Administrativo u WHERE u.name = :name and u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("name", name);
		List<Device> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
    public void deleteUser(String id) {
		String sql = "SELECT u FROM Administrativo u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", id);
		List<Device> list = query.getResultList();
		list.get(0).setDelete(true);
		update(list.get(0));
	}
}

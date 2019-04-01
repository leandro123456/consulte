package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.Admin;

public class AdminDAO extends JPADAO<Admin>{

	@SuppressWarnings("unchecked")
	public List<Admin> retrieveAll() {
		String sql = "SELECT u FROM Admin u WHERE u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		List<Admin> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Admin retrieveById(String userId) {
		String sql = "SELECT u FROM Admin u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", userId);
		List<Admin> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Admin retrieveByName(String name) {
		String sql = "SELECT u FROM Admin u WHERE u.name = :name and u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("name", name);
		List<Admin> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
    public void deleteAdmin(String id) {
		String sql = "SELECT u FROM Admin u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", id);
		List<Admin> list = query.getResultList();
		list.get(0).setDelete(true);
		update(list.get(0));
	}
}

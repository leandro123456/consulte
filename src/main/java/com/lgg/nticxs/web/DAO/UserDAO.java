package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.User;

public class UserDAO extends JPADAO<User>{

	@SuppressWarnings("unchecked")
	public List<User> retrieveAll() {
		String sql = "SELECT u FROM User u WHERE u.delete=false and u.cuenta_iniciada=false";
		Query query = getEntityManager().createQuery(sql);
		List<User> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User retrieveById(String userId) {
		String sql = "SELECT u FROM User u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", userId);
		List<User> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User retrieveByMail(String email) {
		String sql = "SELECT u FROM User u WHERE u.email = :email and u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("email", email);
		List<User> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
    public void deleteUser(String id) {
		String sql = "SELECT u FROM User u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", id);
		List<User> list = query.getResultList();
		list.get(0).setDelete(true);
		update(list.get(0));
	}
}

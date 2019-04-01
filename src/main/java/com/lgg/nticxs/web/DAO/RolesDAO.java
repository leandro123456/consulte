package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.Role;

public class RolesDAO extends JPADAO<Role>{

	@SuppressWarnings("unchecked")
	public List<Role> retrieveAll() {
		String sql = "SELECT r FROM Role r";
		Query query = getEntityManager().createQuery(sql);
		List<Role> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Role retrieveByNameRole(String nameRole) {
		String sql = "SELECT r FROM Role r WHERE r.nameRole = :nameRole";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("nameRole", nameRole);
		List<Role> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
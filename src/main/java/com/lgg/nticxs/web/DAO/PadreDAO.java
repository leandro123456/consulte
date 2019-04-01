package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.Padre;

public class PadreDAO extends JPADAO<Padre>{


	@SuppressWarnings("unchecked")
	public List<Padre> retrieveAll() {
		String sql = "SELECT u FROM Padre u WHERE u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		List<Padre> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Padre retrieveById(String userId) {
		String sql = "SELECT u FROM Padre u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", userId);
		List<Padre> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Padre retrieveByName(String name) {
		String sql = "SELECT u FROM Padre u WHERE u.name = :name and u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("name", name);
		List<Padre> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
    public void deletePadre(String id) {
		String sql = "SELECT u FROM Padre u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", id);
		List<Padre> list = query.getResultList();
		list.get(0).setDelete(true);
		update(list.get(0));
	}
}

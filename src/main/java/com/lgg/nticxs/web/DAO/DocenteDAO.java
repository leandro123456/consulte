package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.Docente;

public class DocenteDAO extends JPADAO<Docente>{


	@SuppressWarnings("unchecked")
	public List<Docente> retrieveAll() {
		String sql = "SELECT u FROM Docente u WHERE u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		List<Docente> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Docente retrieveById(String userId) {
		String sql = "SELECT u FROM Docente u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", userId);
		List<Docente> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Docente retrieveByName(String name) {
		String sql = "SELECT u FROM Docente u WHERE u.name = :name and u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("name", name);
		List<Docente> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
    public void deleteDocente(String id) {
		String sql = "SELECT u FROM Docente u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", id);
		List<Docente> list = query.getResultList();
		list.get(0).setDelete(true);
		update(list.get(0));
	}
}

package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.dbcommands.MongoCommands;
import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.Vista;

public class VistaDAO extends JPADAO<Vista>{
	static final private String COLLECTION= "VISTA";
	static final private String FIELD_NAME= "name";

	@SuppressWarnings("unchecked")
	public List<Vista> retrieveAll() {
		String sql = "SELECT u FROM Vista";
		Query query = getEntityManager().createQuery(sql);
		List<Vista> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Vista retrieveById(String vistaId) {
		String sql = "SELECT u FROM Vista u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", vistaId);
		List<Vista> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Vista retrieveByName(String name) {
		String sql = "SELECT u FROM Vista u WHERE u.name = :name";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("name", name);
		List<Vista> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
    public void deleteByname(String name) {
		MongoCommands.Delete(COLLECTION, FIELD_NAME, name);
	}
}

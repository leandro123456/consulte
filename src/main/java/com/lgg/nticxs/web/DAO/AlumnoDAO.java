package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.Alumno;

public class AlumnoDAO extends JPADAO<Alumno>{

	@SuppressWarnings("unchecked")
	public List<Alumno> retrieveAll() {
		String sql = "SELECT u FROM Alumno u WHERE u.delete=false and u.cuenta_iniciada=false";
		Query query = getEntityManager().createQuery(sql);
		List<Alumno> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Alumno retrieveById(String userId) {
		String sql = "SELECT u FROM Alumno u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", userId);
		List<Alumno> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Alumno retrieveByName(String name) {
		String sql = "SELECT u FROM Alumno u WHERE u.name = :name and u.delete=false";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("name", name);
		List<Alumno> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
    public void deleteUser(String id) {
		String sql = "SELECT u FROM Alumno u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", id);
		List<Alumno> list = query.getResultList();
		list.get(0).setDelete(true);
		update(list.get(0));
	}
}

package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.Docente;
import com.lgg.nticxs.web.model.Documento;

public class DocumentoDAO extends JPADAO<Documento>{

	@SuppressWarnings("unchecked")
	public List<Documento> retrieveAll() {
		String sql = "SELECT u FROM Documento u WHERE u.available = true";
		Query query = getEntityManager().createQuery(sql);
		List<Documento> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Documento retrieveById(String userId) {
		String sql = "SELECT u FROM Documento u WHERE u.id = :id and u.available = true";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", userId);
		List<Documento> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Documento retrieveByName(String name) {
		String sql = "SELECT u FROM Documento u WHERE u.name = :name and u.available = true";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("name", name);
		List<Documento> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> retrieveByMateria(String materia) {
		String sql = "SELECT u FROM Documento u WHERE u.materia = :materia and u.available = true";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("materia", materia);
		List<Documento> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		System.out.println("la lista de docuemntos esta vacia");
		return null;
	}
	
	@SuppressWarnings("unchecked")
    public void deleteDocumento(String id) {
		String sql = "SELECT u FROM Documento u WHERE u.id = :id and u.available = true";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", id);
		List<Documento> list = query.getResultList();
		list.get(0).setAvailable(false);
		update(list.get(0));
	}
	
}

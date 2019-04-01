package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.Nota;

public class NotaDAO extends JPADAO<Nota>{

	@SuppressWarnings("unchecked")
	public List<Nota> retrieveAll() {
		String sql = "SELECT u FROM Nota u";
		Query query = getEntityManager().createQuery(sql);
		List<Nota> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Nota retrieveById(String userId) {
		String sql = "SELECT u FROM Nota u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", userId);
		List<Nota> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Nota> retrieveByAlumno(String idalumno) {
		String sql = "SELECT u FROM Nota u WHERE u.idalumno = :idalumno";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idalumno", idalumno);
		List<Nota> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public Nota retrieveByNameDateDescription(String idalumno, String fecha, String descripcion) {
		String sql = "SELECT u FROM Nota u WHERE u.idalumno LIKE :idalumno and  u.fecha LIKE :fecha and u.descripcion LIKE :descripcion";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idalumno", idalumno);
		query.setParameter("fecha", fecha);
		query.setParameter("descripcion", descripcion);
		List<Nota> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}

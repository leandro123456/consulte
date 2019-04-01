package com.lgg.nticxs.web.DAO;

import java.util.List;

import javax.persistence.Query;

import com.lgg.nticxs.web.jpa.JPADAO;
import com.lgg.nticxs.web.model.Asistencia;

public class AsistenciaDAO extends JPADAO<Asistencia>{

	@SuppressWarnings("unchecked")
	public List<Asistencia> retrieveAll() {
		String sql = "SELECT u FROM Asistencia u";
		Query query = getEntityManager().createQuery(sql);
		List<Asistencia> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Asistencia retrieveById(String userId) {
		String sql = "SELECT u FROM Asistencia u WHERE u.id = :id";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", userId);
		List<Asistencia> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Asistencia> retrieveByAlumno(String idalumno) {
		String sql = "SELECT u FROM Asistencia u WHERE u.idalumno = :idalumno";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idalumno", idalumno);
		List<Asistencia> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public Asistencia retrieveByNameDateDescription(String idalumno, String fecha) {
		String sql = "SELECT u FROM Asistencia u WHERE u.idalumno LIKE :idalumno and  u.fecha LIKE :fecha";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idalumno", idalumno);
		query.setParameter("fecha", fecha);
		List<Asistencia> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}

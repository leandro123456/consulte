package com.lgg.nticxs.web.jpa;

import javax.persistence.EntityManager;

public class JPADAO<T> extends Factory{

	public void create(T entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
	}

	public void update(T entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
	}

	public void delete(T entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
	}

}

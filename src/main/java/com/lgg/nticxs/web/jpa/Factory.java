package com.lgg.nticxs.web.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.lgg.nticxs.web.utils.PropertieValue;

public class Factory {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager em;
	
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public EntityManager getEm() {
		return em;
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public EntityManager getEntityManager() {
		if(em ==null){
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(PropertieValue.DB_NAME);
	    	em = factory.createEntityManager();	
		}
		em.clear();
		return em;
	}
}

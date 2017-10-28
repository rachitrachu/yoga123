package com.yugma.school.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
     
    @PersistenceContext
    private EntityManager entityManager;
 
    protected EntityManager getSession() {
		return this.entityManager;
	 }
 
    public T getByKey(PK key) {
        return (T) getSession().find(persistentClass, key);
    }
 
    public void persist(T entity) {
        getSession().persist(entity);
    }
    
    public T merge(T entity) {
        return getSession().merge(entity);
    }
 
    public void save(T entity){
    	Session session = entityManager.unwrap(Session.class);
    	session.save(entity);
    	session.flush();
    }
    
    public void saveOrUpdate(T entity){
    	Session session = entityManager.unwrap(Session.class);
    	session.saveOrUpdate(entity);
    	session.flush();
    }
    
    public Query update(String hql){
    	Session session = entityManager.unwrap(Session.class);
    	return session.createQuery(hql);
    }
    
    public SQLQuery customQuery(String hql){
    	Session session = entityManager.unwrap(Session.class);
    	return session.createSQLQuery(hql);
    }
    
    public void delete(T entity) {
        getSession().remove(entity);
    }
     
    protected Criteria createEntityCriteria(){
    	Session session = entityManager.unwrap(Session.class);
        return session.createCriteria(persistentClass);
    }
     
}


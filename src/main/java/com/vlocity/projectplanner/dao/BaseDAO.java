package com.vlocity.projectplanner.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.vlocity.projectplanner.entity.BaseEntity;

public abstract class BaseDAO<T extends BaseEntity> {
private final Class<T> entityClass;
    
    @SuppressWarnings("unchecked")
	protected BaseDAO() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }
    
    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Project Planner Persistence Unit");
        return emf.createEntityManager();
    }
    
    public void create(T entity) {
        EntityManager em = null;
        
        try {
           em = getEntityManager();
           em.getTransaction().begin();
           em.persist(entity);
           em.getTransaction().commit();
        } finally {
           if (em != null) {
               em.close();
           }
        }
    } 
    
    public T read(Long id) {
        EntityManager em = getEntityManager();
        
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }
    
    public void update(T entity) {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void delete(T entity) {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
	public List<T> getAllResults() {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            Query query = em.createQuery(new StringBuilder()
                    .append("SELECT t FROM ")
                    .append(entityClass.getSimpleName())
                    .append(" t").toString());
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}

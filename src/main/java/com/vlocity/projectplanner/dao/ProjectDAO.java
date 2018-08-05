package com.vlocity.projectplanner.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vlocity.projectplanner.entity.Project;

public class ProjectDAO extends BaseDAO<Project> {
	
	public ProjectDAO() {
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> getProjectsOrderByProjectName() {
		EntityManager em = null;
        
        try {
            em = getEntityManager();
            Query query = em.createQuery(new StringBuilder()
                    .append("SELECT p FROM ")
                    .append(Project.class.getSimpleName())
                    .append(" p ORDER BY p.projectName ASC").toString());
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
	}
	
}

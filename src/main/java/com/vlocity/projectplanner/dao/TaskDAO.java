package com.vlocity.projectplanner.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vlocity.projectplanner.entity.Project;
import com.vlocity.projectplanner.entity.Task;

public class TaskDAO extends BaseDAO<Task> {

	public TaskDAO() {
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> getTasksByProject(Project project) {
		EntityManager em = null;
        
        try {
            em = getEntityManager();
            Query query = em.createNativeQuery("SELECT * FROM tbl_task WHERE project_id = ?1 ORDER BY id ASC", Task.class);
            query.setParameter(1, project.getId());
            
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
	}
	
}

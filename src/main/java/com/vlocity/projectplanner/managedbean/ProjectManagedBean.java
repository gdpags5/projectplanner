package com.vlocity.projectplanner.managedbean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.vlocity.projectplanner.dao.ProjectDAO;
import com.vlocity.projectplanner.entity.Project;

import com.vlocity.projectplanner.util.FacesUtil;

@RequestScoped
@ManagedBean(name="projectManagedBean")
public class ProjectManagedBean extends BaseManagedBean {

	private Project project;
	private ProjectDAO projectDAO;
	private List<Project> projects;
	
	@PostConstruct
	public void initialize() {
		initializeBase();
		
		project = (Project) session.getAttribute("project");
		
		if (renderedViewing == null) {
            renderedForViewing = false;
        } else {
            renderedForViewing = renderedViewing;
        }
        
        if (renderedEditing == null) {
            renderedForEditing = true;
        } else {
            renderedForEditing = renderedEditing;
        }
        
        if (project == null) {
        	project = new Project();
        }
        
        projectDAO = new ProjectDAO();
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public List<Project> getProjects() {
		projects = projectDAO.getAllResults();
		return projects;
	}
	
	@Override
	public int getTotalRowCount() {
		return projects.size();
	}

	@Override
	public String goToFormPage() {
		session.setAttribute("project", null);
		session.setAttribute("renderedForViewing", null);
		session.setAttribute("renderedForEditing", null);
		
		return "toProjectFormPage";
	}

	@Override
	public String goToListPage() {
		return "toProjectListPage";
	}

	@Override
	public String saveRecord() {
		if (project.getId() != null) {
            try {
                project.setDateUpdated(new Date());
                project.setUpdatedBy("admin");
                
                projectDAO.update(project);
            } catch (Exception ex) {
                FacesUtil.getFacesCtx().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot save this record. There is an existing project id in the database.", null));
                return null;
            }
        } else {
        	project.setDateCreated(new Date());
        	project.setCreatedBy("admin");
        	project.setDateUpdated(new Date());
        	project.setUpdatedBy("admin");
        	
        	projectDAO.create(project);
        }
        
        return "toProjectListPage";
	}

	@Override
	public String viewOrUpdateRecord() {
		Map<String, String> params = FacesUtil.getExternalCtx().getRequestParameterMap();
        String projectId = params.get("projectId");
        String mode = params.get("mode");
        project = projectDAO.read(Long.valueOf(projectId));
        
        if (mode.equals("view")) {
            renderedForViewing = true;
            renderedForEditing = false;
        } 
        
        if (mode.equals("update")) {
            renderedForViewing = false;
            renderedForEditing = true;
        }
        
        session.setAttribute("project", project);
        session.setAttribute("renderedForViewing", renderedForViewing);
        session.setAttribute("renderedForEditing", renderedForEditing);
        
        return "toProjectFormPage";
	}

}

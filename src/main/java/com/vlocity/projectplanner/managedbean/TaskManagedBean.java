package com.vlocity.projectplanner.managedbean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.vlocity.projectplanner.dao.ProjectDAO;
import com.vlocity.projectplanner.dao.TaskDAO;
import com.vlocity.projectplanner.entity.Project;
import com.vlocity.projectplanner.entity.Task;
import com.vlocity.projectplanner.util.FacesUtil;

@ManagedBean(name = "taskManagedBean")
@RequestScoped
public class TaskManagedBean extends BaseManagedBean {

	private Task task;
	private TaskDAO taskDAO;
	private List<Task> tasks;
	
	private Project project;
	private ProjectDAO projectDAO;
	private List<Project> projects;
	
	@PostConstruct
	public void initialize() {
		initializeBase();
		
		task = (Task) session.getAttribute("task");
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
        
        if (task == null) {
        	task = new Task();
        }
        
        taskDAO = new TaskDAO();
        projectDAO = new ProjectDAO();
	}
	
	public Task getTask() {
		return task;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public List<Task> getTasks() {
		tasks = taskDAO.getAllResults();
		return tasks;
	}
	
	public List<Project> getProjects() {
		projects = projectDAO.getProjectsOrderByProjectName();
		return projects;
	}
	
	@Override
	public int getTotalRowCount() {
		return tasks.size();
	}

	@Override
	public String goToFormPage() {
		session.setAttribute("task", null);
		session.setAttribute("renderedForViewing", null);
		session.setAttribute("renderedForEditing", null);
		
		return "toTaskFormPage";
	}

	@Override
	public String goToListPage() {
		return "toTaskListPage";
	}

	@Override
	public String saveRecord() {
		if (task.getId() != null) {
            try {
            	if (task.getId() == task.getDependencyTaskId()) {
            		FacesUtil.getFacesCtx().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Self-task dependency is not allowed.", null));
            		return null;
            	}
            	
            	Task dependentTask = taskDAO.read(task.getDependencyTaskId());
            	
            	if (dependentTask != null) {
            		if (dependentTask.getTaskEnd().before(task.getTaskStart())) {
            			FacesUtil.getFacesCtx().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "The start date of your task should not be ahead of your dependent task.", null));
                		return null;
            		}
            	}
            	
            	task.setProject(project);
                task.setDateUpdated(new Date());
                task.setUpdatedBy("admin");
                
                taskDAO.update(task);
            } catch (Exception ex) {
                FacesUtil.getFacesCtx().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot save this record. There is an existing task id in the database.", null));
                return null;
            }
        } else {
        	Task dependentTask = null;
        	
        	if (task.getDependencyTaskId() != null) {
        		dependentTask = taskDAO.read(task.getDependencyTaskId());
            	
            	if (dependentTask != null) {
            		if (dependentTask.getTaskEnd().after(task.getTaskStart())) {
            			FacesUtil.getFacesCtx().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "The start date of your task should not be ahead of your dependent task.", null));
                		return null;
            		}
            	}
        	}
        	
        	task.setProject(project);
        	task.setDateCreated(new Date());
        	task.setCreatedBy("admin");
        	task.setDateUpdated(new Date());
        	task.setUpdatedBy("admin");
        	
        	taskDAO.create(task);
        }
        
        return "toTaskListPage";
	}

	@Override
	public String viewOrUpdateRecord() {
		Map<String, String> params = FacesUtil.getExternalCtx().getRequestParameterMap();
        String taskId = params.get("taskId");
        String mode = params.get("mode");
        task = taskDAO.read(Long.valueOf(taskId));
        
        if (mode.equals("view")) {
            renderedForViewing = true;
            renderedForEditing = false;
        }
        
        if (mode.equals("update")) {
            renderedForViewing = false;
            renderedForEditing = true;
        }
        
        session.setAttribute("task", task);
        session.setAttribute("renderedForViewing", renderedForViewing);
        session.setAttribute("renderedForEditing", renderedForEditing);
        
        return "toTaskFormPage";
	}

}

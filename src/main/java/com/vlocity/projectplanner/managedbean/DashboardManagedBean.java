package com.vlocity.projectplanner.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import com.vlocity.projectplanner.dao.ProjectDAO;
import com.vlocity.projectplanner.dao.TaskDAO;
import com.vlocity.projectplanner.entity.Project;
import com.vlocity.projectplanner.entity.Task;

@ManagedBean(name = "dashboardManagedBean")
@RequestScoped
public class DashboardManagedBean extends BaseManagedBean {

	private ScheduleModel projectScheduleModel;
	
	private List<Task> tasks;
	private List<Project> projects;
	
	private TaskDAO taskDAO;
	private ProjectDAO projectDAO;
	
	@PostConstruct
	public void initialize() {
		initializeBase();
		
		projectDAO = new ProjectDAO();
		
		// 1.  Get all the available projects.
		projects = projectDAO.getAllResults();
		
		if (!projects.isEmpty()) {
			// 2.  Instantiate the Primefaces' Schedule component and render it.
			projectScheduleModel = new DefaultScheduleModel();
			renderedForViewing = true;
			
			taskDAO = new TaskDAO();
			
			for (Project project : projects) {
				// 3.  Get all the available tasks based on its project.
				tasks = taskDAO.getTasksByProject(project);
				
				if (!tasks.isEmpty()) {
					// 4.  Iterate over the tasks and set it as a schedule event for the scheduler.
					for (Task task : tasks) {
						String projectAndTaskDescription = new StringBuilder()
								.append(task.getProject().getProjectName())
								.append(" - ")
								.append(task.getTaskName()).toString();
						
						projectScheduleModel.addEvent(new DefaultScheduleEvent(projectAndTaskDescription, task.getTaskStart(), task.getTaskEnd()));
					}
				}
			}
		} else {
			projectScheduleModel = new DefaultScheduleModel();
			renderedForViewing = false;
		}
	}
	
	public ScheduleModel getProjectScheduleModel() {
		return projectScheduleModel;
	}
	
	@Override
	public int getTotalRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String goToFormPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String goToListPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viewOrUpdateRecord() {
		// TODO Auto-generated method stub
		return null;
	}

}

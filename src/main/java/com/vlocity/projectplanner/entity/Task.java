package com.vlocity.projectplanner.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tbl_task")
public class Task extends BaseEntity implements Serializable {
	
	/**
	 * Default serial version id of this class.
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="task_name", length=150, nullable=false)
	private String taskName;
	
	@Column(name="task_description", length=225, nullable=false)
	private String taskDescription;
	
	@Column(name="task_assigned_to", length=225)
	private String taskAssignedTo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="task_start", nullable=false)
	private Date taskStart;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="task_end", nullable=false)
	private Date taskEnd;
	
	@Column(name="dependency_task_id")
	private Long dependencyTaskId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
	private Project project;
	
	public Task() {
	}
	
	public Task(Long id,
			String taskName,
			String taskDescription,
			String taskAssignedTo,
			Date taskStart,
			Date taskEnd,
			Long dependencyTaskId,
			Project project) {
		this.id = id;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskAssignedTo = taskAssignedTo;
		this.taskStart = taskStart;
		this.taskEnd = taskEnd;
		this.dependencyTaskId = dependencyTaskId;
		this.project = project;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskAssignedTo() {
		return taskAssignedTo;
	}

	public void setTaskAssignedTo(String taskAssignedTo) {
		this.taskAssignedTo = taskAssignedTo;
	}

	public Date getTaskStart() {
		return taskStart;
	}

	public void setTaskStart(Date taskStart) {
		this.taskStart = taskStart;
	}

	public Date getTaskEnd() {
		return taskEnd;
	}

	public void setTaskEnd(Date taskEnd) {
		this.taskEnd = taskEnd;
	}

	public Long getDependencyTaskId() {
		return dependencyTaskId;
	}

	public void setDependencyTaskId(Long dependencyTaskId) {
		this.dependencyTaskId = dependencyTaskId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        
        Task other = (Task) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("com.vlocity.projectplanner.Task[ id=")
                .append(id).append(" ]").toString();
    }
	
}

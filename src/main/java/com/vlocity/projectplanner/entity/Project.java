package com.vlocity.projectplanner.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbl_project")
public class Project extends BaseEntity implements Serializable {

	/**
	 * Default serial version id of class.
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="project_name", length=150, nullable=false)
	private String projectName;
	
	@Column(name="project_description", length=225)
	private String projectDescription;
	
	@Column(name="client_name", length=225, nullable=false)
	private String clientName;
	
	@Column(name="project_manager")
	private String projectManager;
	
	public Project() {
	}
	
	public Project(Long id,
			String projectName,
			String projectDescription,
			String clientName,
			String projectManager,
			String createdBy,
			Date dateCreated,
			String updatedBy,
			Date dateUpdated) {
		this.id = id;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.clientName = clientName;
		this.projectManager = projectManager;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		this.updatedBy = updatedBy;
		this.dateUpdated = dateUpdated;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
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
        if (!(object instanceof Project)) {
            return false;
        }
        
        Project other = (Project) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("com.vlocity.projectplanner.Project[ id=")
                .append(id).append(" ]").toString();
    }
	
}

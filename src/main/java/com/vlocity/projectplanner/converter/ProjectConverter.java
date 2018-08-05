package com.vlocity.projectplanner.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.vlocity.projectplanner.dao.ProjectDAO;
import com.vlocity.projectplanner.entity.Project;

@ManagedBean(name = "projectConverter")
public class ProjectConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
            return null;
        } else {
            Project project = new ProjectDAO().read(Long.valueOf(arg2));
            return project;
        }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {
            return ((Project) arg2).getId().toString();
        } else {
            return "";
        }
	}

}

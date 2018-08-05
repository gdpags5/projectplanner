package com.vlocity.projectplanner.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.vlocity.projectplanner.dao.TaskDAO;
import com.vlocity.projectplanner.entity.Task;

@ManagedBean(name = "taskConverter")
public class TaskConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
            return null;
        } else {
            Task task = new TaskDAO().read(Long.valueOf(arg2));
            
            if (task != null) {
            	return task.getId();
            } else {
            	return null;
            }
        }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {
            return arg2.toString();
        } else {
            return "";
        }
	}

}

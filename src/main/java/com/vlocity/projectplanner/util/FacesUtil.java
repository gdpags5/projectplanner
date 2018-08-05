package com.vlocity.projectplanner.util;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A JavaServer Faces utility class that can always be used by the managed beans.
 * @author Gerald D. Paguio
 */
public class FacesUtil {
    
    public static FacesContext getFacesCtx() {
        return FacesContext.getCurrentInstance();
    }
    
    public static ExternalContext getExternalCtx() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }
    
    public static Application getApplication() {
        return FacesContext.getCurrentInstance().getApplication();
    }
    
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }
    
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    public static Flash getFlash() {
        return (Flash) FacesContext.getCurrentInstance().getExternalContext().getFlash();
    }
}

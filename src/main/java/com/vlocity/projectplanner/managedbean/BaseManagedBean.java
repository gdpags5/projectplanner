package com.vlocity.projectplanner.managedbean;

import javax.servlet.http.HttpSession;

import com.vlocity.projectplanner.util.FacesUtil;

public abstract class BaseManagedBean {

	protected boolean renderedForViewing;
    protected boolean renderedForEditing;
    protected Boolean renderedViewing;
    protected Boolean renderedEditing;
    protected HttpSession session;
    
    protected BaseManagedBean() {
    }
    
    /**
     * This method initializes the rendition 
     * of the components of the Facelet page.
     */
    protected void initializeBase() {
        session = FacesUtil.getSession();
        renderedViewing = (Boolean) session.getAttribute("renderedForViewing");
        renderedEditing = (Boolean) session.getAttribute("renderedForEditing");
    }
    
    /**
     * Get method of renderedForViewing attribute.
     * 
     * @return <b>true</b> or <b>false</b>
     */
    public boolean isRenderedForViewing() {
        return renderedForViewing;
    }
    
    /**
     * Set method of renderedForViewing attribute.
     * 
     * @param renderedForViewing 
     */
    public void setRenderedForViewing(boolean renderedForViewing) {
        this.renderedForViewing = renderedForViewing;
    }
    
    /**
     * Get method of renderedForEditing attribute.
     * 
     * @return <b>true</b> or <b>false</b>
     */
    public boolean isRenderedForEditing() {
        return renderedForEditing;
    }
    
    /**
     * Set method of renderedForEditing attribute.
     * 
     * @param renderedForEditing 
     */
    public void setRenderedForEditing(boolean renderedForEditing) {
        this.renderedForEditing = renderedForEditing;
    }
    
    /**
     * An abstract method that returns the total 
     * number of records from the list.
     * 
     * @return The total row count.
     */
    public abstract int getTotalRowCount();
    
    /**
     * An abstract method that provides the navigation 
     * to input form pages(i.e. employeeForm.xhtml)
     * 
     * @return The navigation value to input form page. 
     */
    public abstract String goToFormPage();
    
    /**
     * An abstract method that provides the navigation 
     * to list pages(i.e. employeeList.xhtml)
     * 
     * @return The navigation value to list page.
     */
    public abstract String goToListPage();
    
    /**
     * An abstract method that saves the record 
     * from the input form page into the database table.
     * 
     * @return The navigation value after saving the record. 
     */
    public abstract String saveRecord();
    
    /**
     * An abstract method that views or updates a certain record 
     * from the data table and displays in the input form page.
     * 
     * @return The navigation value for viewing or updating the record.
     */
    public abstract String viewOrUpdateRecord();
	
}

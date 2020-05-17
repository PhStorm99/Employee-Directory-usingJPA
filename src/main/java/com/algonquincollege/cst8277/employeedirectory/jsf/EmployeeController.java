/***************************************************************************f******************u************zz*******y**

 * File: EmployeeController.java
 * Course materials (20W) CST 8277
 * 
 * @author (original) Mike Norman
 * @author Harsh Patel 040919280
 *
 */
package com.algonquincollege.cst8277.employeedirectory.jsf;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.algonquincollege.cst8277.employeedirectory.dao.EmployeeDao;
import com.algonquincollege.cst8277.employeedirectory.model.EmployeePojo;

/**
 * Description: Responsible for collection of Employee DTO's in XHTML (list) <h:dataTable> </br>
 * Delegates all C-R-U-D behaviour to DAO
 */
@Named
@SessionScoped
public class EmployeeController implements Serializable {
    private static final long serialVersionUID = 1L;

    protected FacesContext facesContext;
    protected ServletContext sc;
    protected EmployeeDao employeeDao;
    protected List<EmployeePojo> employees;
    
    /**
     * Variable boolean showView used in the toogleAddingShow method to hide/show thing
     */
    private boolean showview;
    
    @Inject
    public EmployeeController(FacesContext facesContext, ServletContext sc, EmployeeDao employeeDao) {
        this.facesContext = facesContext;
        this.sc = sc;
        this.employeeDao = employeeDao;
    }

    public void loadEmployees() {
        sc.log("refreshing employees");
        employees = employeeDao.readAllEmployees();
    }

    public List<EmployeePojo> getEmployees() {
        return this.employees;
    }
    public void setEmployees(List<EmployeePojo> employees) {
        this.employees = employees;
    }

    /**
     * Toggles the add employee mode which determines whether the addEmployee form is rendered
     */
    public void toggleAddingShow()
    {  
        showview = !showview;
    }
    public boolean isShowView()
    {
        return showview;
    }
    public void setShowView(boolean showview)
    {
        this.showview=showview;
    }
    
    public String editEmployee(EmployeePojo employee) {
        return null; //current page
    }

    public String updateEmployee(EmployeePojo employee) {
        EmployeePojo employeeToUpdateEmployeed = employeeDao.readEmployeeById(employee.getId());
        if (employeeToUpdateEmployeed == null) {
            // someone else deleted it
            facesContext.addMessage(null, new FacesMessage(SEVERITY_ERROR, "Employee record missing, please refresh", null));
        }
        else {
            employeeToUpdateEmployeed = employeeDao.updateEmployee(employee);
            if (employeeToUpdateEmployeed == null) {
                // OptimisticLockException - someone else altered it 'faster' than we could hit the 'save' button
                facesContext.addMessage(null, new FacesMessage(SEVERITY_ERROR, "Employee record out-of-date, please refresh", null));
            }
            else {
                employeeToUpdateEmployeed.setEditable(false);
                int idx = employees.indexOf(employee);
                employees.remove(idx);
                employees.add(idx, employeeToUpdateEmployeed);
            }
        }
        return null; //current page
    }

    public String cancelUpdate(EmployeePojo employee) {
        return null; //current page
    }

    public void deleteEmployee(int empId) {
        EmployeePojo employeePojoToBeRemoted = employeeDao.readEmployeeById(empId);
        if (employeePojoToBeRemoted != null) {
            employeeDao.deleteEmployeeById(empId);
            employees.remove(employeePojoToBeRemoted);
        }
    }

    /**
     * Method to call the New Employee View that can successfully add the employee to the list
     * @param theNewEmployee
     * 
     */
    public void addNewEmployee(EmployeePojo theNewEmployee) {
        
        //calling create employee to the Employee Pojo from the dao
        EmployeePojo pojo = employeeDao.createEmployee(theNewEmployee);
        employees.add(pojo);
        
    }
}
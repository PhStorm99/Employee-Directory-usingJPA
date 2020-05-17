/**
 * File : NewEmployeeView.java
 * Course materials (20W) CST 8277
 * 
 * @author (original) Mike Norman
 * @author Harsh Patel 040919280
 * 
 */
package com.algonquincollege.cst8277.employeedirectory.jsf;

import java.io.Serializable;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algonquincollege.cst8277.employeedirectory.model.EmployeePojo;

/**
 * Description: Responsible for the adding the new employee view and checcking its conditions by setting 
 * every variable to the form and checking the conditions if its is null or not null
 */
@Named
@ViewScoped
public class NewEmployeeView implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * Member Fields -> firstName, lastName, email, title, salary for the form
     */
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String title;
    protected Double salary;

    @Inject
    @ManagedProperty("#{employeeController}")
    protected EmployeeController employeeController;

    /**
     * Empty Constructor
     */
    public NewEmployeeView() {
        
    }
    
    /**
     * Getting the firstnmae
     * @return  firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * setting the firstname
     * @param firstName  firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getting the lastname
     * @return  lastName
     */
    
    public String getLastName() {
        return lastName;
    }
    /**
     * Setting the LastName
     * @param LastName  LastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getting the email
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * 
     * @param email email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Getting the title
     * @return title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Setting the title
     * @param title Title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Getting the salary
     * @return salary
     */
    public Double getSalary() {
        return salary; 
    }
    /**
     * Setting the salary
     * @param salary
     */
    public void setSalary(Double salary)
    {
        this.salary = salary;
    }
   
    /**
     * Method for adding the employee and calling the newemployeeform and toggleadding from employeeController
     * 
     */
    public void addEmployee() {
        if (allNotNullOrEmpty(firstName, lastName, email, title, salary)) {
            EmployeePojo theNewEmployee = new EmployeePojo();
            theNewEmployee.setFirstName(getFirstName());
            theNewEmployee.setLastName(getLastName());
            theNewEmployee.setEmail(getEmail());
            theNewEmployee.setTitle(getTitle());
            theNewEmployee.setSalary(getSalary());

            // this Managed Bean does not know how to 'do' anything, ask controller
            employeeController.addNewEmployee(theNewEmployee);

            //clean up
            employeeController.toggleAddingShow();
            
            setFirstName(null);
            setLastName(null);
            setEmail(null);
            setTitle(null);
            setSalary(null);
        }
    }

    /**
     * method to check the conditions and implements it accordingly
     * 
     * @param values
     * @return true 
     */
    static boolean allNotNullOrEmpty(final Object... values) {
        if (values == null) {
            return false;
        }
        for (final Object val : values) {
            if (val == null) {
                return false;
            }
            if (val instanceof String) {
                String str = (String)val;
                if (str.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}
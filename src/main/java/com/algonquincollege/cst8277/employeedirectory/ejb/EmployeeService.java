/***************************************************************************f******************u************zz*******y**

 * File: EmployeeService.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman   
 * @author Harsh Patel 040919280 
 *
 */
package com.algonquincollege.cst8277.employeedirectory.ejb;

import static com.algonquincollege.cst8277.employeedirectory.model.EmployeePojo.ALL_EMPLOYEES_QUERY_NAME;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.employeedirectory.model.EmployeePojo;

/**
 * EJB EmployeeService : Managed Bean class
 */

@Stateless
public class EmployeeService implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(name="employeeDirectory-PU")
    protected EntityManager em;

    /**
     * Default constructor.
     */
    public EmployeeService() {
    }

    /**
     * Here is the method of finding all employees
     * @return returning all list
     */
    public List<EmployeePojo> findAllEmployees() {
        TypedQuery<EmployeePojo> allEmpsQuery = em.createNamedQuery(ALL_EMPLOYEES_QUERY_NAME, EmployeePojo.class);
        return allEmpsQuery.getResultList();
    }

    /**
     * Persist Employee method
     * @param employee
     * @return employee
     */
    @Transactional
    public EmployeePojo persistEmployee(EmployeePojo employee) {
        em.persist(employee);
        return employee;
    }

    /**
     * Method to find individual employee by their unique primary key
     * @param empPK
     * @return empPK
     */
    public EmployeePojo findEmployeeByPrimaryKey(int empPK) {
        return em.find(EmployeePojo.class, empPK);
    }
    
    /**
     * Method to merge the employee for saving the updates
     * @param employeeWithUpdates
     * @return mergedEmployeePojo variable
     */
    public EmployeePojo mergeEmployee(EmployeePojo employeeWithUpdates) {
        EmployeePojo mergedEmployeePojo = em.merge(employeeWithUpdates);
        return mergedEmployeePojo;
    }

    /**
     * Method to delete the employee by their id which is the primary key
     * @param employeeId
     */
    @Transactional
    public void removeEmployee(int employeeId) {
        EmployeePojo employee = findEmployeeByPrimaryKey(employeeId);
        if (employee != null) {
            em.refresh(employee);
            em.remove(employee);
        }
    }

}
/***************************************************************************f******************u************zz*******y**
 * File: EmployeeDao.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 *
 */
package com.algonquincollege.cst8277.employeedirectory.dao;

import java.util.List;

import com.algonquincollege.cst8277.employeedirectory.model.EmployeePojo;

/**
 * Description: API for the database C-R-U-D operations
 */
public interface EmployeeDao {

    // C
    public EmployeePojo createEmployee(EmployeePojo employee);
    // R
    public EmployeePojo readEmployeeById(int employeeId);
    public List<EmployeePojo> readAllEmployees();
    // U
    public EmployeePojo updateEmployee(EmployeePojo employee);
    // D
    public void deleteEmployeeById(int employeeId);

}
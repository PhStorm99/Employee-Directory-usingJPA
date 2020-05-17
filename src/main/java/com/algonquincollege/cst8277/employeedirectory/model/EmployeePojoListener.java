/***************************************************************************f******************u************zz*******y**

 * File: EmployeeDTO.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * @author Harsh Patel 040919280
 * 
 */
package com.algonquincollege.cst8277.employeedirectory.model;

import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Description : EmployeePojoListener class which sets the created and updated date 
 * by calling them
 * 
 */
public class EmployeePojoListener {
    LocalDateTime now = LocalDateTime.now();

    /**
     * Create Date
     * @param emp
     */
    @PrePersist
    public void setCreatedOnDate(EmployeePojo emp) {
        emp.setCreatedDate(now);        
    }

    /**
     * Update Date
     * @param emp
     */
    @PreUpdate
    public void setUpdatedDate(EmployeePojo emp) {
        emp.setUpdatedDate(LocalDateTime.now());
    }
}
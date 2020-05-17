/**
 * File: SalaryGreaterThanZeroValidator.java
 * Course materials (20W) CST 8277
 * 
 * @author (original) Mike Norman
 * @author Harsh Patel 040919280
 * 
 */
package com.algonquincollege.cst8277.employeedirectory.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Description : This is the Salary Validator which is implemeting the validator by conditioning the value entered
 * and throwing the Validator Exception of message
 * 
 */
@FacesValidator("salaryGreaterThanZeroValidator")
public class SalaryGreaterThanZeroValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // TODO Auto-generated method stub
        
        Double value1  = (Double)value;

            // Putting if conditon in Validator if it is null or less than zero, show this message
            if (value1 == null || value1 < 0) {
            
            FacesMessage message = new FacesMessage("Salary must be greater than zero");
            
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
          
            }
    }
}


   
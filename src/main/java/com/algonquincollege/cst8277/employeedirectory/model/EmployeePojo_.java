package com.algonquincollege.cst8277.employeedirectory.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-05-17T14:52:25.240-0400")
@StaticMetamodel(EmployeePojo.class)
public class EmployeePojo_ {
	public static volatile SingularAttribute<EmployeePojo, Integer> version;
	public static volatile SingularAttribute<EmployeePojo, Integer> id;
	public static volatile SingularAttribute<EmployeePojo, String> firstName;
	public static volatile SingularAttribute<EmployeePojo, String> lastName;
	public static volatile SingularAttribute<EmployeePojo, String> email;
	public static volatile SingularAttribute<EmployeePojo, String> title;
	public static volatile SingularAttribute<EmployeePojo, Double> salary;
	public static volatile SingularAttribute<EmployeePojo, LocalDateTime> createdDate;
	public static volatile SingularAttribute<EmployeePojo, LocalDateTime> updatedDate;
}

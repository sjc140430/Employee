package com.scoopman.payroll;

import lombok.Data;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Data
@Entity
class Employee {
	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	private Date date; //sql.date
	private String role;
	
	protected Employee() {}
	
	Employee(String name, String role) {
		this.firstName=name;
		this.role=role;
	}
}

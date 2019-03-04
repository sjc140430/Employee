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
	private String date; //sql.date
	private String phoneNumber;
	private String address;
	
	protected Employee() {}
	
	Employee(String name, String lastName, String date, String phoneNumber, String address) {
		this.firstName=name;
		this.lastName=lastName;
		this.date=date;
		this.phoneNumber=phoneNumber;
		this.address=address;
	}
}

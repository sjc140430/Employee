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
	private String laptopType;
	private String plannedDate;
	private String actualDate;
	private String DOB;
	private String wiproEmail;
	private String wiproID;
	private String aadhaar;
	private String phoneNumber;
	private String address;


	
	protected Employee() {}
	
	Employee(String name, String lastName, String laptopType, String plannedDate, String actualDate, String DOB, String wiproEmail, String wiproID, String aadhaar, String phoneNumber, String address) {
		this.firstName=name;
		this.lastName=lastName;
		this.laptopType=laptopType;
		this.plannedDate=plannedDate;
		this.actualDate=actualDate;
		this.DOB=DOB;
		this.wiproEmail=wiproEmail;
		this.wiproID=wiproID;
		this.aadhaar=aadhaar;
		this.phoneNumber=phoneNumber;
		this.address=address;
	}
}

package com.scoopman.payroll;

import java.sql.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {

	private final EmployeeRepository repository;
	
	EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	@GetMapping("/employees")
	List<Employee> all() {
		return repository.findAll();
	}

	@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}

	// Single item

	@GetMapping("/employees/{id}")
	Employee one(@PathVariable Long id) {

		return repository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@PutMapping("/employees/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		
		return repository.findById(id)
			.map(employee -> {
				employee.setFirstName(newEmployee.getFirstName());
				employee.setLastName(newEmployee.getLastName());
				employee.setLaptopType(newEmployee.getLaptopType());
				employee.setPlannedDate(newEmployee.getPlannedDate());
				employee.setActualDate(newEmployee.getActualDate());
				employee.setDOB(newEmployee.getDOB());
				employee.setWiproEmail(newEmployee.getWiproEmail());
				employee.setWiproID(newEmployee.getWiproID());
				employee.setAadhaar(newEmployee.getAadhaar());
				employee.setPhoneNumber(newEmployee.getPhoneNumber());
				employee.setAddress(newEmployee.getAddress());
				
				return repository.save(employee);
				/*
				 * 	private @Id @GeneratedValue Long id;
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
				 */
			})
			.orElseGet(() -> {
				newEmployee.setId(id);
				return repository.save(newEmployee);
			});
	}

	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
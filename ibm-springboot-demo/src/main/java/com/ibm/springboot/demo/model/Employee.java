package com.ibm.springboot.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("employees")
public class Employee {

	@Id
	private String employeeId;

	private String firstName;

	private String email;

//	private Long aadhaar;

	private Double salary;

	public Employee() {
		super();
	}

	public Employee(String firstName, Double salary) {
		super();
		this.firstName = firstName;
		this.salary = salary;
	}

	public Employee(String employeeId, String firstName, Double salary) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.salary = salary;
	}

	public Employee(String employeeId, String firstName, String email, Double salary) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.email = email;
//		this.aadhaar = aadhaar;
		this.salary = salary;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Long getAadhaar() {
//		return aadhaar;
//	}
//
//	public void setAadhaar(Long aadhaar) {
//		this.aadhaar = aadhaar;
//	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", email=" + email + ", salary=" + salary + "]";
	}

}

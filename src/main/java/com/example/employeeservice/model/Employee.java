package com.example.employeeservice.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long employeeId;
	@NotNull
	private String first_name;
	@NotNull
	private String last_name;
	@NotNull
	private String email;
	@NotNull
	private String title;
	public String getFirst_name() {
		return first_name;
	}
	
	public Employee() {}
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeById(Long id) {
		employeeId = id;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}

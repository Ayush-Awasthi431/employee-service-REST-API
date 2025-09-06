package com.example.employeeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.exception.EmployeeNotFoundException;
import com.example.employeeservice.model.Employee;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public void setEmployeeContainer(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(Long id) {
		Employee obj = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		return obj;
	}
	
	public Employee saveEmployee(Employee e) {
		return employeeRepository.save(e);
	}
	
	public void updateEmployee(Long id , Employee e) {
		Employee existing = getEmployeeById(id);
	    existing.setFirst_name(e.getFirst_name());
	    existing.setLast_name(e.getLast_name());
	    existing.setEmail(e.getEmail());
	    existing.setTitle(e.getTitle());
	    employeeRepository.save(existing);
	}
	
	public void deleteEmployee(Long id) {
		getEmployeeById(id);
		employeeRepository.deleteById(id);
	}
}

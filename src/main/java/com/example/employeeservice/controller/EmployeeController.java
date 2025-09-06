package com.example.employeeservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
	
	@Autowired
	public void setEmployeeContainer(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
	
	@RequestMapping(method = RequestMethod.GET , produces = "application/json")
	public List<Employee> getEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET , produces = "application/json")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") Long id) {
	    Employee obj = employeeService.getEmployeeById(id);
	    return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST , consumes = "application/json" , produces = "application/json")
	public ResponseEntity<Object> postEmployee(@RequestBody @Valid Employee e1) {
		Employee saved =  employeeService.saveEmployee(e1);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getEmployeeId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(path = "/{id}",method = RequestMethod.PUT , produces = "application/json")
	public ResponseEntity<Void> updateEmployee(@PathVariable(name = "id") Long id , @RequestBody @Valid Employee e){
		employeeService.updateEmployee(id, e);
	    return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable(name = "id") Long id){
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}

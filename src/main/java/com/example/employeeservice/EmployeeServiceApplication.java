package com.example.employeeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;

@SpringBootApplication
public class EmployeeServiceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
	
    private EmployeeRepository employeeRepository;
	
	@Autowired
	public void setEmployeeContainer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		for(int i = 1 ; i < 6 ; i++) {
			Employee e = new Employee();
			e.setFirst_name("Ayush");
			e.setLast_name("Awasthi" + i);
			e.setEmail("ayush" + i + "@gmail.com");
			e.setTitle("Mr.");
			employeeRepository.save(e);
		}
	}
}

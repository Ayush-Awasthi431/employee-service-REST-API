package com.example.employeeservice.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType; 
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.employeeservice.controller.EmployeeController;
import com.example.employeeservice.exception.EmployeeNotFoundException;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	private ObjectMapper mapper = new ObjectMapper();
	private Employee e1;
	private Employee e2;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).setControllerAdvice(new com.example.employeeservice.exception.EmployeeAdvice()).build();
		e1 = new Employee();
		e1.setFirst_name("Ayush");
		e1.setLast_name("Awasthi");
		e1.setTitle("Mr.");
		e1.setEmail("ayushawasthi@gmail.com");
		
		e2 = new Employee();
		e2.setFirst_name("Shivam");
		e2.setLast_name("Kumar");
		e2.setTitle("Mr.");
		e2.setEmail("shivamkumar@gmail.com");
	}
	
	@Test
	void testGetAllEmployees() throws Exception {
		List<Employee> list = Arrays.asList(e1,e2);
		when(employeeService.getAllEmployees()).thenReturn(list);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employees").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].first_name").value("Ayush"))
		.andExpect(jsonPath("$[1].first_name").value("Shivam"));
	}
	
	@Test
	void testGetEmployeeByIdSuccess() throws Exception{
	  e1.setEmployeeById(1L);
	  when(employeeService.getEmployeeById(1L)).thenReturn(e1);
	  
	  mockMvc.perform(MockMvcRequestBuilders.get("/employees/1").accept(MediaType.APPLICATION_JSON))
	  .andExpect(status().isOk())
	  .andExpect(jsonPath("$.first_name").value("Ayush"));
	}
	
	@Test
	void testGetEmployeeById_NotFound() throws Exception {
	    when(employeeService.getEmployeeById(1L)).thenThrow(new EmployeeNotFoundException(1L));

	    mockMvc.perform(MockMvcRequestBuilders.get("/employees/1")
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$.error").value("Not Found"));
	}
	
	@Test
	void testPostEmployee() throws Exception {
	    e1.setEmployeeById(1L);
	    when(employeeService.saveEmployee(any(Employee.class))).thenReturn(e1);

	    mockMvc.perform(MockMvcRequestBuilders.post("/employees")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(mapper.writeValueAsString(e1)))
	            .andExpect(status().isCreated())
	            .andExpect(header().exists("Location"));
	}
	
	@Test
	void testUpdateEmployee() throws Exception {
	    e1.setEmployeeById(1L);

	    when(employeeService.getEmployeeById(1L)).thenReturn(e1);

	    doNothing().when(employeeService).updateEmployee(eq(1L), any(Employee.class));

	    mockMvc.perform(MockMvcRequestBuilders.put("/employees/1")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(mapper.writeValueAsString(e1)))
	            .andExpect(status().isNoContent());
	}

	@Test
	void testDeleteEmployee() throws Exception {
	    e1.setEmployeeById(1L);
	    doNothing().when(employeeService).deleteEmployee(1L);

	    mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1"))
	            .andExpect(status().isNoContent());
	}


}

package com.example.employeeservice.exception;

import java.util.List;

public class ValidationErrorDTO {
	
	private String message;
	private String error;
	private List<String> details;
	
	public ValidationErrorDTO(String message , String error , List<String> details) {
		this.details = details;
		this.error = error;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
}

package com.example.employeeservice.exception;

public class MessageDTO {

	private String message;
	private String error;
	
	public MessageDTO() {
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
}

package com.example.employeeservice.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeAdvice {

	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public MessageDTO handleNullPointerException(EmployeeNotFoundException exception) {
		MessageDTO m = new MessageDTO();
		m.setMessage(exception.getMessage());
		m.setError("Not Found");
		return m;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ValidationErrorDTO handleNotValidException(MethodArgumentNotValidException exception) {
		List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
		
		return new ValidationErrorDTO("Incorrect Employee","Validation Error",errors);
	}
}

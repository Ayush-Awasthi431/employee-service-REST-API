# Employee REST API

This project was developed as part of the **HPE Virtual Internship via Forage**.  
It is a **Spring Boot REST API** for managing employee data, supporting CRUD operations with validation, exception handling, and unit tests.

## Features
- GET all employees
- GET employee by ID
- POST new employee
- PUT update existing employee
- DELETE employee
- Input validation using `@NotNull`
- Exception handling for invalid inputs and not-found resources
- Unit tests using MockMvc and JUnit 5

## Technologies
- Java
- Spring Boot
- Spring Data JPA
- H2 / MySQL (or any DB)
- MockMvc, JUnit 5
- Gradle

## Setup & Run
### 1. Clone the repository:
	git clone <https://github.com/Ayush-Awasthi431/employee-service-REST-API.git>

### 2. Open the project in Eclipse or IntelliJ (no command needed).

### 3. Build the project:
	./gradlew build

### 4. Run the Spring Boot application:
	./gradlew bootRun

### 5. Access endpoints via Postman or browser:
### (Use these HTTP methods and endpoints)

	GET    /employees
	GET    /employees/{id}
	POST   /employees
	PUT    /employees/{id}
	DELETE /employees/{id}

	
## Testing

Unit tests for controller endpoints using MockMvc are located in 	src/test/java/com/example/employeeservice/controllertest/

Run tests:

	./gradlew test
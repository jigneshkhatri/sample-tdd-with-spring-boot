# Employee Management System

## Project Overview
This is sample Spring Boot project to demonstrate TDD, unit testing and integration testing with JUnit 5. 

The Employee Management System (EMS) is a Spring Boot-based application designed to manage employee information within an organization. The system will handle CRUD (Create, Read, Update, Delete) operations for employees, departments, and roles. This application will serve as a foundational project to understand and implement core Spring Boot features, including RESTful APIs, data persistence using JPA/Hibernate, validation, exception handling, and security.

## Features
1. **Employee Management:**
    - Add, update, view, and delete employee details.
    - Search employees by name, department, or role.

2. **Department Management:**
    - Manage departments with the ability to add, update, and delete department information.
    - View the list of employees in each department.

3. **Role Management:**
    - Define roles (e.g., Developer, Manager, HR) with specific permissions.
    - Assign roles to employees.

4. **Authentication & Authorization:**
    - Implement JWT-based authentication.
    - Role-based access control to restrict access to certain endpoints.

5. **Validation:**
    - Implement input validation for the employee, department, and role entities.
    - Handle and display error messages for invalid data inputs.

6. **Testing:**
    - Write unit tests for service layer components.
    - Write integration tests for REST APIs.

## Expected Outcomes
- **RESTful APIs:** A set of well-documented APIs that allow clients to perform CRUD operations on employees, departments, and roles.
- **Data Persistence:** A fully functioning database setup using Spring Data JPA/Hibernate, with entities mapped correctly to corresponding tables.
- **Security:** Secure endpoints with role-based access control, ensuring only authorized users can perform certain actions.
- **Validation:** Robust validation mechanisms that prevent invalid data from being persisted.
- **Error Handling:** A structured approach to handling errors, providing meaningful feedback to users and clients.
- **Testing:** A comprehensive suite of tests that ensure the reliability and correctness of the application.

## Technologies Used
- **Spring Boot:** For building the application.
- **Spring Data JPA:** For ORM and database interaction.
- **H2:** H2 in-memory database is used.
- **Spring Security:** For authentication and authorization.
- **JUnit & Mockito:** For testing.


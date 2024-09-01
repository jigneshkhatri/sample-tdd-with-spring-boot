package in.quallit.tdd_practice.controllers;

import in.quallit.tdd_practice.entities.Employee;
import in.quallit.tdd_practice.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jigs
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/secure/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") Integer employeeId) {
        Employee employee = this.employeeService.findById(employeeId);
        return ResponseEntity.ok(employee);
    }
}

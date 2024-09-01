package in.quallit.tdd_practice.services;

import in.quallit.tdd_practice.entities.Employee;
import in.quallit.tdd_practice.exceptions.DataNotFoundException;
import in.quallit.tdd_practice.exceptions.InputValidationException;
import in.quallit.tdd_practice.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jigs
 */
@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findById(Integer employeeId) {
        // input validations
        if (employeeId == null || employeeId < 1) {
            throw new InputValidationException("employeeId should be valid positive integer");
        }

        Optional<Employee> employeeOptional = this.employeeRepository.findById(employeeId);
        return employeeOptional.orElseThrow(() -> new DataNotFoundException(String.format("Employee with id=[%d] not found", employeeId)));
    }
}

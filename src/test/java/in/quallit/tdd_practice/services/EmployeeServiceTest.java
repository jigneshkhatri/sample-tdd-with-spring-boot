package in.quallit.tdd_practice.services;

import in.quallit.tdd_practice.entities.Employee;
import in.quallit.tdd_practice.exceptions.DataNotFoundException;
import in.quallit.tdd_practice.exceptions.InputValidationException;
import in.quallit.tdd_practice.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jigs
 */
@ExtendWith({MockitoExtension.class})
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void findById_shouldReturnEmployeeDetailsIfEmployeeExists() {
        // preparation
        Integer employeeId = 1;
        Employee dummyEmployee = new Employee();
        dummyEmployee.setId(employeeId);
        Mockito.when(this.employeeRepository.findById(employeeId)).thenReturn(Optional.of(dummyEmployee));

        // execution
        Employee employee = this.employeeService.findById(employeeId);

        // assertions
        assertNotNull(employee);
        assertEquals(employeeId, employee.getId());
    }

    @Test
    void findById_shouldThrowExceptionIfEmployeeDoesNotExists() {
        Integer employeeId = 2;
        assertThrows(DataNotFoundException.class, () -> this.employeeService.findById(employeeId));
    }

    @Test
    void findById_shouldThrowExceptionIfEmployeeIdIsNotValid() {
        Integer employeeId1 = null;
        Integer employeeId2 = 0;
        Integer employeeId3 = -1;

        assertThrows(InputValidationException.class, () -> this.employeeService.findById(employeeId1));
        assertThrows(InputValidationException.class, () -> this.employeeService.findById(employeeId2));
        assertThrows(InputValidationException.class, () -> this.employeeService.findById(employeeId3));
    }
}

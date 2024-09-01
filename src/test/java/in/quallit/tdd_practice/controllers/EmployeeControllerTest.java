package in.quallit.tdd_practice.controllers;

import in.quallit.tdd_practice.entities.Employee;
import in.quallit.tdd_practice.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Jigs
 */
@ExtendWith({MockitoExtension.class})
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test()
    public void findById_shouldReturnEmployeeDetailsIfEmployeeExists() {
        // preparation
        Integer employeeId = 1;
        Employee dummyEmployee = new Employee();
        dummyEmployee.setId(employeeId);
        Mockito.when(this.employeeService.findById(employeeId)).thenReturn(dummyEmployee);

        // execution
        ResponseEntity<Employee> employeeResponseEntity = this.employeeController.findById(employeeId);

        // assertions
        assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), employeeResponseEntity.getStatusCode());
        assertNotNull(employeeResponseEntity.getBody());
        assertEquals(employeeId, employeeResponseEntity.getBody().getId());
    }
}

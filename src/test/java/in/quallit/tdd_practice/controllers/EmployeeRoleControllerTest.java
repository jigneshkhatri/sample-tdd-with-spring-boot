package in.quallit.tdd_practice.controllers;

import in.quallit.tdd_practice.entities.EmployeeRole;
import in.quallit.tdd_practice.services.EmployeeRoleService;
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
class EmployeeRoleControllerTest {

    @Mock
    private EmployeeRoleService employeeRoleService;
    @InjectMocks
    private EmployeeRoleController employeeRoleController;

    @Test()
    void save_shouldAssignRoleToEmployee() {
        // preparation
        Integer employeeId = 1;
        Integer roleId = 1;
        EmployeeRole employeeRole = new EmployeeRole(employeeId, roleId);
        Mockito.when(this.employeeRoleService.saveOrUpdate(employeeRole)).thenAnswer(val -> {
            EmployeeRole temp = val.getArgument(0, EmployeeRole.class);
            temp.setId(1);
            return temp;
        });

        // execution
        ResponseEntity<EmployeeRole> employeeRoleResponseEntity = this.employeeRoleController.save(employeeRole);

        // assertions
        assertEquals(HttpStatusCode.valueOf(HttpStatus.CREATED.value()), employeeRoleResponseEntity.getStatusCode());
        assertNotNull(employeeRoleResponseEntity.getBody());
        assertNotNull(employeeRoleResponseEntity.getBody().getId());
        assertEquals(employeeId, employeeRoleResponseEntity.getBody().getEmployeeId());
        assertEquals(roleId, employeeRoleResponseEntity.getBody().getRoleId());
    }
}

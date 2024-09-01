package in.quallit.tdd_practice.services;

import in.quallit.tdd_practice.entities.EmployeeRole;
import in.quallit.tdd_practice.exceptions.InputValidationException;
import in.quallit.tdd_practice.repositories.EmployeeRoleRepository;
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
class EmployeeRoleServiceTest {

    @Mock
    private EmployeeRoleRepository employeeRoleRepository;
    @InjectMocks
    private EmployeeRoleService employeeRoleService;

    @Test()
    void saveOrUpdate_shouldSaveTheEmployeeRoleIfNotAlreadyExists() {
        // preparation
        Integer employeeId = 1;
        Integer roleId = 1;
        EmployeeRole employeeRole = new EmployeeRole(employeeId, roleId);
        Mockito.when(this.employeeRoleRepository.findByEmployeeId(employeeId)).thenReturn(Optional.empty());
        Mockito.when(this.employeeRoleRepository.save(employeeRole)).thenAnswer(val -> {
            EmployeeRole temp = val.getArgument(0, EmployeeRole.class);
            temp.setId(1);
            return temp;
        });

        // execution
        EmployeeRole savedEmployeeRole = this.employeeRoleService.saveOrUpdate(employeeRole);

        // assertions
        assertNotNull(savedEmployeeRole);
        assertNotNull(savedEmployeeRole.getId());
        assertEquals(1, savedEmployeeRole.getId());
        assertEquals(employeeId, savedEmployeeRole.getEmployeeId());
        assertEquals(roleId, savedEmployeeRole.getRoleId());
    }

    @Test()
    void saveOrUpdate_shouldUpdateIfAlreadyExists() {
        // preparation
        Integer employeeId = 1;
        Integer roleId = 2;
        EmployeeRole employeeRole = new EmployeeRole(employeeId, roleId);

        EmployeeRole alreadyExists = new EmployeeRole(employeeId, 1);
        alreadyExists.setId(1);
        Optional<EmployeeRole> optionalAlreadyExists = Optional.of(alreadyExists);
        Mockito.when(this.employeeRoleRepository.findByEmployeeId(employeeId)).thenReturn(optionalAlreadyExists);
        Mockito.when(this.employeeRoleRepository.save(optionalAlreadyExists.get())).thenAnswer(val -> {
            EmployeeRole temp = val.getArgument(0, EmployeeRole.class);
            temp.setRoleId(roleId);
            return temp;
        });

        // execution
        EmployeeRole savedEmployeeRole = this.employeeRoleService.saveOrUpdate(employeeRole);

        // assertions
        assertNotNull(savedEmployeeRole);
        assertNotNull(savedEmployeeRole.getId());
        assertEquals(1, savedEmployeeRole.getId());
        assertEquals(employeeId, savedEmployeeRole.getEmployeeId());
        assertEquals(roleId, savedEmployeeRole.getRoleId());
    }

    @Test()
    void saveOrUpdate_shouldThrowExceptionIfInvalidDataPassed() {
        // preparation
        Integer employeeId1 = 1;
        Integer roleId1 = 0;
        EmployeeRole employeeRole1 = new EmployeeRole(employeeId1, roleId1);

        Integer employeeId2 = 0;
        Integer roleId2 = 1;
        EmployeeRole employeeRole2 = new EmployeeRole(employeeId2, roleId2);

        EmployeeRole employeeRole3 = null;

        EmployeeRole employeeRole4 = new EmployeeRole(employeeId2, roleId1);

        // execution
        assertThrows(InputValidationException.class, () -> this.employeeRoleService.saveOrUpdate(employeeRole1));
        assertThrows(InputValidationException.class, () -> this.employeeRoleService.saveOrUpdate(employeeRole2));
        assertThrows(InputValidationException.class, () -> this.employeeRoleService.saveOrUpdate(employeeRole3));
        assertThrows(InputValidationException.class, () -> this.employeeRoleService.saveOrUpdate(employeeRole4));
    }
}

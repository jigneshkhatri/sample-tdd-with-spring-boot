package in.quallit.tdd_practice.controllers;

import in.quallit.tdd_practice.entities.Role;
import in.quallit.tdd_practice.enums.RoleCode;
import in.quallit.tdd_practice.services.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jigs
 */
@ExtendWith({MockitoExtension.class})
class RoleControllerTest {

    @Mock
    private RoleService roleService;
    @InjectMocks
    private RoleController roleController;

    @Test()
    void findAll_shouldReturnAllTheRoles() {
        // preparation
        List<Role> dummyRoles = new ArrayList<>();
        Role dummyRole = new Role();
        dummyRole.setId(1);
        dummyRole.setCode(RoleCode.DEVELOPER);
        dummyRoles.add(dummyRole);
        Mockito.when(this.roleService.findAll()).thenReturn(dummyRoles);

        // execution
        ResponseEntity<List<Role>> roleResponseEntity = this.roleController.findAll();

        // assertions
        assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), roleResponseEntity.getStatusCode());
        assertNotNull(roleResponseEntity.getBody());
        assertFalse(roleResponseEntity.getBody().isEmpty());
    }
}

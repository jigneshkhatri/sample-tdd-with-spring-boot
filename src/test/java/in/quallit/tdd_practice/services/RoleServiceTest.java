package in.quallit.tdd_practice.services;

import in.quallit.tdd_practice.entities.Role;
import in.quallit.tdd_practice.enums.RoleCode;
import in.quallit.tdd_practice.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Jigs
 */
@ExtendWith({MockitoExtension.class})
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private RoleService roleService;


    @Test()
    void findAll_shouldReturnAllRoles() {
        // preparation
        List<Role> dummyRoles = new ArrayList<>();
        Role dummyRole = new Role();
        dummyRole.setId(1);
        dummyRole.setCode(RoleCode.DEVELOPER);
        dummyRoles.add(dummyRole);
        Mockito.when(roleRepository.findAll()).thenReturn(dummyRoles);

        // execution
        List<Role> roles = this.roleService.findAll();

        // assertions
        assertNotNull(roles);
        assertFalse(roles.isEmpty());
    }
}

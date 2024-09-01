package in.quallit.tdd_practice.features;

import in.quallit.tdd_practice.dtos.AuthResponse;
import in.quallit.tdd_practice.entities.EmployeeRole;
import in.quallit.tdd_practice.entities.Role;
import in.quallit.tdd_practice.entities.Employee;
import in.quallit.tdd_practice.enums.RoleCode;
import in.quallit.tdd_practice.extensions.AuthResponseParameterResolver;
import in.quallit.tdd_practice.extensions.AuthSetupBeforeAllExtension;
import in.quallit.tdd_practice.extensions.AuthSetupBeforeEachExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Jigs
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith({AuthSetupBeforeAllExtension.class, AuthSetupBeforeEachExtension.class, AuthResponseParameterResolver.class})
class RoleManagementFeatureTest {

    public static final int EMPLOYEE_ID = 2;
    private RestTemplate restTemplate = new RestTemplateBuilder().build();

    @BeforeAll
    public static void beforeAll() {

    }

    @BeforeEach
    public void beforeEach() {}

    @Test()
    void employeeShouldBeAssignedWithARole(AuthResponse authResponse) {
        // get an employee
        // get list of roles
        // pick a role
        // assign employee a role

        String getAEmployeeAPIUrl = "http://localhost:8080/employees/secure/{employeeId}";
        String getRolesAPIUrl = "http://localhost:8080/roles/secure";
        String assignEmployeeARoleAPIUrl = "http://localhost:8080/employee-role/secure";
        String authToken = authResponse.getToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(authToken);
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Employee> employeeResponseEntity = this.restTemplate.exchange(getAEmployeeAPIUrl, HttpMethod.GET, httpEntity, Employee.class, EMPLOYEE_ID);
        ResponseEntity<List<Role>> rolesResponseEntity = this.restTemplate.exchange(getRolesAPIUrl, HttpMethod.GET,
                httpEntity, new ParameterizedTypeReference<>() {});

        // assert that valid employee should be returned
        assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), employeeResponseEntity.getStatusCode());
        assertNotNull(employeeResponseEntity.getBody());
        assertNotNull(employeeResponseEntity.getBody().getId());

        // assert that role list is not empty
        assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), rolesResponseEntity.getStatusCode());
        assertNotNull(rolesResponseEntity.getBody());
        assertFalse(rolesResponseEntity.getBody().isEmpty());

        Employee employee = employeeResponseEntity.getBody();

        // filter the required role
        Role developerRole = rolesResponseEntity.getBody().stream().filter(x -> RoleCode.DEVELOPER.equals(x.getCode())).findFirst().orElse(null);

        // assert that required role is found
        assertNotNull(developerRole);
        assertNotNull(developerRole.getId());

        // prepare employee role object and call POST API to assign role to employee
        EmployeeRole employeeRole = new EmployeeRole(employee.getId(), developerRole.getId());
        HttpEntity httpEntityToPost = new HttpEntity<>(employeeRole, httpHeaders);
        ResponseEntity<EmployeeRole> employeeRoleResponseEntity = this.restTemplate.exchange(assignEmployeeARoleAPIUrl, HttpMethod.POST, httpEntityToPost, EmployeeRole.class);

        // assert that role is successfully assigned to the employee
        assertEquals(HttpStatusCode.valueOf(HttpStatus.CREATED.value()), employeeRoleResponseEntity.getStatusCode());
        assertNotNull(employeeRoleResponseEntity.getBody());
        assertNotNull(employeeRoleResponseEntity.getBody().getId());
    }
}

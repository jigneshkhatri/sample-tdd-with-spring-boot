package in.quallit.tdd_practice.extensions;

import in.quallit.tdd_practice.entities.Employee;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Jigs
 */
public class AuthSetupBeforeAllExtension implements BeforeAllCallback {
    private final Logger logger = LoggerFactory.getLogger(AuthSetupBeforeAllExtension.class);
    private RestTemplate restTemplate = new RestTemplateBuilder().build();
    private static final String USER_REGISTRATION_URL = "http://localhost:8080/auth/register";
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        logger.info("Initializing the dummy user");

        // Getting spring context, just to initialize the spring application
        ApplicationContext applicationContext = SpringExtension.getApplicationContext(extensionContext);
        Environment environment = applicationContext.getEnvironment();
        String username = environment.getProperty("dummy.user.username");
        String password = environment.getProperty("dummy.user.password");

        registerEmployee(username, password);
        registerEmployee("2" + username, "2" + password);
    }

    private void registerEmployee(String username, String password) {
        Employee employee = new Employee();
        employee.setEmail(username);
        employee.setPassword(password);

        ResponseEntity<Employee> registeredUserResponseEntity = this.restTemplate.postForEntity(USER_REGISTRATION_URL,
                employee, Employee.class);

        assertEquals(HttpStatusCode.valueOf(HttpStatus.CREATED.value()), registeredUserResponseEntity.getStatusCode());
        assertNotNull(registeredUserResponseEntity.getBody().getId());
    }
}

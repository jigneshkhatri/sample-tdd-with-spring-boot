package in.quallit.tdd_practice.extensions;

import in.quallit.tdd_practice.dtos.AuthRequest;
import in.quallit.tdd_practice.dtos.AuthResponse;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Jigs
 */
@Component
public class AuthSetupBeforeEachExtension implements BeforeEachCallback {
    private final Logger logger = LoggerFactory.getLogger(AuthSetupBeforeEachExtension.class);
    private RestTemplate restTemplate = new RestTemplateBuilder().build();
    private static final String USER_AUTHENTICATION_URL = "http://localhost:8080/auth/authenticate";

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        logger.info("Authenticating dummy user");

        // Getting spring context, just to initialize the spring application
        ApplicationContext applicationContext = SpringExtension.getApplicationContext(extensionContext);
        Environment environment = applicationContext.getEnvironment();
        String username = environment.getProperty("dummy.user.username");
        String password = environment.getProperty("dummy.user.password");

        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(username);
        authRequest.setPassword(password);

        ResponseEntity<AuthResponse> authResponseResponseEntity = this.restTemplate.postForEntity(USER_AUTHENTICATION_URL,
                authRequest, AuthResponse.class);

        assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), authResponseResponseEntity.getStatusCode());
        assertNotNull(authResponseResponseEntity.getBody().getToken());

        // register a callback hook for when the root test context is shut down
        extensionContext
                .getRoot()
                .getStore(ExtensionContext.Namespace.GLOBAL).put("token", authResponseResponseEntity.getBody());
    }
}

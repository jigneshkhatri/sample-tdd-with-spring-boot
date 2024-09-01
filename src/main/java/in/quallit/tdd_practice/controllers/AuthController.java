package in.quallit.tdd_practice.controllers;

import in.quallit.tdd_practice.dtos.AuthRequest;
import in.quallit.tdd_practice.dtos.AuthResponse;
import in.quallit.tdd_practice.entities.Employee;
import in.quallit.tdd_practice.services.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jigs
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        Employee registeredEmployee = this.authService.register(employee);
        return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CREATED.value())).body(registeredEmployee);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        String token = this.authService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(authResponse);
    }
}

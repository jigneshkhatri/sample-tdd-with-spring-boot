package in.quallit.tdd_practice.controllers;

import in.quallit.tdd_practice.entities.EmployeeRole;
import in.quallit.tdd_practice.services.EmployeeRoleService;
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
@RequestMapping("/employee-role")
public class EmployeeRoleController {

    private EmployeeRoleService employeeRoleService;

    public EmployeeRoleController(EmployeeRoleService employeeRoleService) {
        this.employeeRoleService = employeeRoleService;
    }
    @PostMapping("/secure")
    public ResponseEntity<EmployeeRole> save(@RequestBody EmployeeRole employeeRole) {
        EmployeeRole savedEmployeeRole = this.employeeRoleService.saveOrUpdate(employeeRole);
        return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CREATED.value())).body(savedEmployeeRole);
    }
}

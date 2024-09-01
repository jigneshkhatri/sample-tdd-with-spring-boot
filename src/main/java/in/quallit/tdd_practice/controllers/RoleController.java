package in.quallit.tdd_practice.controllers;

import in.quallit.tdd_practice.entities.Role;
import in.quallit.tdd_practice.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jigs
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping("/secure")
    public ResponseEntity<List<Role>> findAll() {
        List<Role> roles = this.roleService.findAll();
        return ResponseEntity.ok(roles);
    }
}

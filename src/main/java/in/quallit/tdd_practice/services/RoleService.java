package in.quallit.tdd_practice.services;

import in.quallit.tdd_practice.entities.Role;
import in.quallit.tdd_practice.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jigs
 */
@Service
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }
}

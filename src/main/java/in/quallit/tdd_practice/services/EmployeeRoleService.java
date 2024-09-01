package in.quallit.tdd_practice.services;

import in.quallit.tdd_practice.entities.EmployeeRole;
import in.quallit.tdd_practice.exceptions.InputValidationException;
import in.quallit.tdd_practice.repositories.EmployeeRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jigs
 */
@Service
@Transactional(readOnly = true)
public class EmployeeRoleService {

    private EmployeeRoleRepository employeeRoleRepository;

    public EmployeeRoleService(EmployeeRoleRepository employeeRoleRepository) {
        this.employeeRoleRepository = employeeRoleRepository;
    }

    @Transactional
    public EmployeeRole saveOrUpdate(EmployeeRole employeeRole) {
        // input validation
        if (employeeRole == null || employeeRole.getEmployeeId() < 1 || employeeRole.getRoleId() < 1) {
            throw new InputValidationException("employeeRole should not be null and should contain valid values for employeeId and roleId");
        }

        // if employee already has some role, then just update the role instead of inserting new
        Optional<EmployeeRole> employeeRoleOptional = this.employeeRoleRepository.findByEmployeeId(employeeRole.getEmployeeId());
        if (employeeRoleOptional.isPresent()) {
            // update
            EmployeeRole employeeRoleToUpdate = employeeRoleOptional.get();
            employeeRoleToUpdate.setRoleId(employeeRole.getRoleId());
            return this.employeeRoleRepository.save(employeeRoleToUpdate);
        }

        // save
        return this.employeeRoleRepository.save(employeeRole);
    }
}

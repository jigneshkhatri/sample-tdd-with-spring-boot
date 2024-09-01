package in.quallit.tdd_practice.repositories;

import in.quallit.tdd_practice.entities.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Jigs
 */
@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {
    Optional<EmployeeRole> findByEmployeeId(Integer employeeId);
}

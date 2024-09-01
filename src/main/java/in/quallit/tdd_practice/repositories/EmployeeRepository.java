package in.quallit.tdd_practice.repositories;

import in.quallit.tdd_practice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Jigs
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);
}

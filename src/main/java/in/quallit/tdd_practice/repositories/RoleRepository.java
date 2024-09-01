package in.quallit.tdd_practice.repositories;

import in.quallit.tdd_practice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jigs
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}

package in.quallit.tdd_practice.services.security;

import in.quallit.tdd_practice.entities.Employee;
import in.quallit.tdd_practice.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Jigs
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
    private EmployeeRepository employeeRepository;

    public CustomUserDetailService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = this.employeeRepository.findByEmail(username);
        if (!employee.isPresent()) {
            throw new UsernameNotFoundException("Username does not exists");
        }

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.emptyList();
            }

            @Override
            public String getPassword() {
                return employee.get().getPassword();
            }

            @Override
            public String getUsername() {
                return employee.get().getEmail();
            }
        };
    }
}

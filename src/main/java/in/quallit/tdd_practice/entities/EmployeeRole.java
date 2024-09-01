package in.quallit.tdd_practice.entities;

import jakarta.persistence.*;

/**
 * @author Jigs
 */
@Entity
@Table(name = "employee_role")
public class EmployeeRole {
    private Integer employeeId;
    private Integer roleId;
    private Integer id;

    public EmployeeRole(Integer employeeId, Integer roleId) {
        this.employeeId = employeeId;
        this.roleId = roleId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "employee_id")
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "role_id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}

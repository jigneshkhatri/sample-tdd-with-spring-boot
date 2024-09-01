package in.quallit.tdd_practice.entities;

import in.quallit.tdd_practice.enums.RoleCode;
import jakarta.persistence.*;

/**
 * @author Jigs
 */
@Entity
@Table(name = "role")
public class Role {

    private Integer id;
    private String name;
    private RoleCode code;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code")
    public RoleCode getCode() {
        return code;
    }

    public void setCode(RoleCode code) {
        this.code = code;
    }
}

package userworker.model;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "role", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}

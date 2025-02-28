package identity.TuanHuy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false)
    private identity.TuanHuy.entity.RoleEnum name;

    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

    @ManyToMany
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "permission_name")
    )
    private Set<Permission> permissions;

}

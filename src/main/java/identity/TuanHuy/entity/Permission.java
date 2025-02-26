package identity.TuanHuy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @Column(name = "name", unique = true, nullable = false) // Thêm annotation để đảm bảo mapping chính xác
    private String name;
    private String code;
    private String description;
    private Boolean isActive;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

}

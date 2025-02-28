package identity.TuanHuy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    public Permission(String name, String code, String description, Boolean isActive) {
    }



}

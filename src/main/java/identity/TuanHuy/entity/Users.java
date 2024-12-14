package identity.TuanHuy.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Table(name = "Users")
@Setter
@Getter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    private LocalDate dob;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Songs> songs; // Danh sách bài hát của user


}
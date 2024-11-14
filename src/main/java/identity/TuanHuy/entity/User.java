package identity.TuanHuy.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    private LocalDate dob;
}
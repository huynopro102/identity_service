package identity.TuanHuy.repository;


import identity.TuanHuy.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Boolean existsByUsername(String username);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsername(String username);
}

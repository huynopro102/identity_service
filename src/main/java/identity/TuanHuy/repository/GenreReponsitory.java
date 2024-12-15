package identity.TuanHuy.repository;

import identity.TuanHuy.entity.Genre;
import identity.TuanHuy.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GenreReponsitory extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}

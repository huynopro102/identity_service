package identity.TuanHuy.repository;

import identity.TuanHuy.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReponsitory extends JpaRepository<Posts, String> {

}

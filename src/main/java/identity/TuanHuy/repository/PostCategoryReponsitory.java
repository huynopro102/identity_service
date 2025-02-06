package identity.TuanHuy.repository;

import identity.TuanHuy.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostCategoryReponsitory extends JpaRepository<PostCategory, UUID> {

}

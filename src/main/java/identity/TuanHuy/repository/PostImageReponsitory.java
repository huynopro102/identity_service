package identity.TuanHuy.repository;

import identity.TuanHuy.entity.PostImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostImageReponsitory extends JpaRepository<PostImages, UUID> {
    List<PostImages> findByPostId(String postId);
}

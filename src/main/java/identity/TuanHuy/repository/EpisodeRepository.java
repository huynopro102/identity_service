package identity.TuanHuy.repository;

import identity.TuanHuy.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode,String> {
        Optional<Episode> findByNameEpisode(String nameEpisode);
}

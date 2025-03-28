package identity.TuanHuy.repository;

import identity.TuanHuy.entity.PodcastSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PodcastSeriesRepository extends JpaRepository<PodcastSeries,String> {
        boolean existsByTitle(String title);

}

package identity.TuanHuy.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;


@Setter
@Getter
@Entity
@Table(name = "podcast_series")
@AllArgsConstructor
@NoArgsConstructor
public class PodcastSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "title" , length = 255 ,unique = true)
    private String title; // String in java -> in database is varchar default length is 255 character ,
    // the storage of accented characters depends on the encoding configured when database is created

    @Column(name = "description" , length = 1000)
    private String description;
    private String author;
    private String narrator;

    @Column(name = "total_duration",nullable = true)
    private Integer totalDuration;

    @Column(name = "cover_url",nullable = false)
    private String coverUrl;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "create_update")
    private LocalDateTime createUpdate;

    @OneToMany(mappedBy = "podcastSeries")
    @JsonManagedReference
    private Set<Episode> episodes;
    // mapped by có nghĩa là phía chủ ( là Episode ) podcastSeries là 1 field trong Episode
    // note : podcastSeries dự vào tên field của của entity tức là "private PodcastSeries podcastSeries" chứ ko phải @joinColumn(name ="podcast_series_id")
}

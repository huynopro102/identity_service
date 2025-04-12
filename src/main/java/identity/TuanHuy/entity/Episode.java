package identity.TuanHuy.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "episode")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "name_episode" , unique = true)
    private String nameEpisode;

    @Column(name = "number_episode" , unique = true)
    private Integer numberEpisode;

    @Column(name = "audio_url")
    private String audio_url;

    @Column(name = "cover_episode")
    private String coverEpisode;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @ManyToOne()
    @JoinColumn(name = "podcast_series_id" , foreignKey = @ForeignKey(name = "fk_podcast_series"))
    @JsonBackReference
    @JsonIgnore
    private PodcastSeries podcastSeries;
}
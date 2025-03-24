package identity.TuanHuy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EpisodeRequest {
    private String podcastSeriesId;
    private String audioUrl;
    private String coverChapter;
    private int duration;
    private String nameEpisode;
    private String numberEpisode;
}

package identity.TuanHuy.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EpisodeResponse {
    private String id;
    private String podcastSeriesId;
    private String audioUrl;
    private String coverEpisode;
    private int duration;
    private String nameEpisode;
    private String numberEpisode;
}

package identity.TuanHuy.dto.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PodcastSeriesRequest {
    private String author;
    private String coverUrl;
    private String description;
    private String narrator;
    private String title;
    private int totalDuration;
}

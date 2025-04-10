package identity.TuanHuy.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PodcastSeriesRequest {

    @Schema(example =  "ANTOINE DE SAINT-EXUPÉRY")
    private String author;

    @Schema(example = "mô tả về hoàng tử bé")
    private String description;

    @Schema(example = "MƯA RADIO")
    private String narrator;

    @Schema(example = "Hoàng Tử Bé")
    private String title;

    private int totalDuration;
}

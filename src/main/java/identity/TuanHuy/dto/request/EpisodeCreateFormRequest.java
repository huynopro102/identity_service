package identity.TuanHuy.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EpisodeCreateFormRequest {
    private String podcastSeriesId;

    @Schema(description = "file audio",type = "string",format = "binary")
    private MultipartFile audioUrl;

    @Schema(description = "hãy mô tả về chương sách này",type = "string", format = "binary")
    private MultipartFile coverChapter;

    @Schema(example = "2000")
    private int duration;
    @Schema(example = "harry potter và tên tù nhân ngục azkaban")
    private String nameEpisode;

    @Schema(example = "3")
    private int numberEpisode;
}

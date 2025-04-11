package identity.TuanHuy.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class PodcastSeriesFormRequest {
    @Schema(example =  "ANTOINE DE SAINT-EXUPÉRY")
    private String author;

    @Schema(example = "Hoàng tử bé (tên tiếng Pháp: Le Petit Prince, phát âm [lə p (ə)ti pʁɛ̃s]), được xuất bản năm 1943, là tiểu thuyết nổi tiếng nhất của nhà văn và phi công Pháp Antoine de Saint-Exupéry. Ông đã thuê ngôi biệt thự The Bevin House ở Asharoken, Long Island, New York trong khi viết tác phẩm này.")
    private String description;

    @Schema(example = "MƯA RADIO")
    private String narrator;

    @Schema(example = "Hoàng Tử Bé")
    private String title;

    @Schema(description = "cover image file",type = "string", format = "binary")
    private MultipartFile coverImage;

    @Schema(example = "12000")
    private int totalDuration;
}

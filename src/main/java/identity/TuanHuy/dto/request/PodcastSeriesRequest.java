package identity.TuanHuy.dto.request;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PodcastSeriesRequest {

    @Schema(example =  "ANTOINE DE SAINT-EXUPÉRY")
    private String author;

    @Schema(example = "Hoàng tử bé (tên tiếng Pháp: Le Petit Prince, phát âm [lə p (ə)ti pʁɛ̃s]), được xuất bản năm 1943, là tiểu thuyết nổi tiếng nhất của nhà văn và phi công Pháp Antoine de Saint-Exupéry. Ông đã thuê ngôi biệt thự The Bevin House ở Asharoken, Long Island, New York trong khi viết tác phẩm này.")
    private String description;

    @Schema(example = "MƯA RADIO")
    private String narrator;

    @Schema(example = "Hoàng Tử Bé")
    private String title;


    private int totalDuration;
}

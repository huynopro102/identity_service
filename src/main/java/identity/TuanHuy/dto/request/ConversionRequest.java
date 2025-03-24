package identity.TuanHuy.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConversionRequest {
    private String youtubeLink;
    private Integer quaity; // chất lượng bit rate tính bằng kbps

}

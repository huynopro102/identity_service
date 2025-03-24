package identity.TuanHuy.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConversionResponse {
    private String mp3Link;
    private String error;
    public ConversionResponse(String mp3Link) {
        this.mp3Link = mp3Link;
    }
}

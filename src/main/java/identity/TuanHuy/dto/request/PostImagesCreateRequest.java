package identity.TuanHuy.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostImagesCreateRequest {

    private String postId;
    private String imageUrl;
    private String imageDescription;

}

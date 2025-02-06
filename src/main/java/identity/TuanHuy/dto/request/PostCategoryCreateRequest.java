package identity.TuanHuy.dto.request;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@RequiredArgsConstructor
public class PostCategoryCreateRequest {
    private String name;
    private String description;
}

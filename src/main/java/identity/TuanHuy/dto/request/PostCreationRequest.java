package identity.TuanHuy.dto.request;


import identity.TuanHuy.entity.Songs;
import identity.TuanHuy.entity.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostCreationRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @NotNull(message = "isPublished status must be specified")
    private Boolean isPublished;

    @NotBlank(message = "User ID cannot be blank")
    private String userId;

    @NotNull(message = "Category IDs cannot be null")
    private List<Long> categoryIds;

    private List<String> imageUrls;  // Optional field

}

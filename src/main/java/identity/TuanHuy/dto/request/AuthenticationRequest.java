package identity.TuanHuy.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class AuthenticationRequest {
    @NotBlank
    @Schema(example = "huynopro102@gmail.com")
    private String email;


    @NotBlank
    @Schema(example = "12345678")
    private String password;
    // Lombok will automatically generate getEmail() and getPassword() with @Data

}

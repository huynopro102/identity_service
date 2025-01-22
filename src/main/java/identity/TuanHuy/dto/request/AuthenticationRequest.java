package identity.TuanHuy.dto.request;

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
    private String email;
    @NotBlank
    private String password;
    // Lombok will automatically generate getEmail() and getPassword() with @Data

}

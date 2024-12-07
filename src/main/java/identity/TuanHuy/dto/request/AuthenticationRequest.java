package identity.TuanHuy.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class AuthenticationRequest {
    private String email;
    private String password;
    // Lombok will automatically generate getEmail() and getPassword()
}

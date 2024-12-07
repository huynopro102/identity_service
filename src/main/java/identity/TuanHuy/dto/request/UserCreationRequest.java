package identity.TuanHuy.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Builder
@Data
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotNull(message = "USERNAME_NOT_NULL")
    @Size(min = 4, message = "USER_INVALID")
    String username;

    @NotNull(message = "PASSWORD_NOT_NULL")
    @Size(min = 8 , message = "PASSWORD_INVALID")
    String password;

    @NotNull(message = "DOB_NOT_NULL")
    LocalDate dob;

    @NotNull(message = "EMAIL_NOT_NULL")
    String email;
}

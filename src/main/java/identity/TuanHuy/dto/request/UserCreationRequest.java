package identity.TuanHuy.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserCreationRequest {
    @NotNull(message = "USERNAME_NOT_NULL")
    @Size(min = 4, message = "USER_INVALID")
    private String username;

    @NotNull(message = "PASSWORD_NOT_NULL")
    @Size(min = 8 , message = "PASSWORD_INVALID")
    private String password;

    @NotNull(message = "DOB_NOT_NULL")
    private LocalDate dob;

    @NotNull(message = "EMAIL_NOT_NULL")
    private String email;
}

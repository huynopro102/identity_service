package identity.TuanHuy.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import identity.TuanHuy.validation.DobConstraint;
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
    @DobConstraint(min = 18 , message = "INVALID_DOB")
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dob;

    @NotBlank(message = "EMAIL_INVALID")
    String email;
}

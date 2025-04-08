package identity.TuanHuy.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import identity.TuanHuy.validation.DobConstraint;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "nguyen tuan huy",description = "enter name user")
    String username;

    @NotNull(message = "PASSWORD_NOT_NULL")
    @Size(min = 8 , message = "PASSWORD_INVALID")
    @Schema(example = "12345678" , description = "enter password")
    String password;

    @NotNull(message = "DOB_NOT_NULL")
    @DobConstraint(min = 18 , message = "INVALID_DOB")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    //shape = JsonFormat.Shape.STRING , để đảm bảo JSON trả về cũng theo format dd-MM-yyyy.
//    @Schema(example = "02-09-1999", description = "enter date of birth (dd-MM-yyyy)")
    @Schema(example = "2003-09-16", description = "enter date of birth (yyyy-MM-dd)")
    LocalDate dob;


    @NotBlank(message = "EMAIL_INVALID")
    @Schema(example = "huynopro102@gmail.com",description = "enter email")
    String email;
}

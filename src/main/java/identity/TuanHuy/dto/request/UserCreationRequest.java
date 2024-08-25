package identity.TuanHuy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserCreationRequest {
    @NotBlank(message = "ko rong , ko khoang trang , ko null")
    @Size(min = 4, message = "USER_INVALID")
    private String username;

    @NotBlank(message = "ko rong , ko khoang trang , ko null")
    @Min(value = 8 , message = "password at least 8 charactor")
    private String password;

    @NotNull(message = "ko rong ,ko null")
    private LocalDate dob;

    @NotBlank(message = "ko rong , ko khoang trang , ko null")
    private String email;
}

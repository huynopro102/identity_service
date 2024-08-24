package identity.TuanHuy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserCreationRequest {
    @Size(min = 4, message = "username at least 4 charactor")
    private String username;
    @Min(value = 8 , message = "password at least 8 charactor")
    private String password;
    private LocalDate dob;
    private String email;
}

package identity.TuanHuy.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserUpdateRequest {
    private String username;
    private String password;
    private LocalDate dob;
    private String email;
}

package identity.TuanHuy.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Builder
@Data
@Setter
@Getter
public class UserUpdateRequest {
    private String username;
    private String password;
    private LocalDate dob;
    private String email;
}

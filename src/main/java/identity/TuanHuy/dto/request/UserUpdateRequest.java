package identity.TuanHuy.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private String username;
    private String password;
    private LocalDate dob;
    private String email;
    private String profileImage;
    private Boolean emailVerified;
}

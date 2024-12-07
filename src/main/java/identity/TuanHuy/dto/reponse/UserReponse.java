package identity.TuanHuy.dto.reponse;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserReponse {
     String id;
     String username;
     String password;
     LocalDate dob;
     String email;
}
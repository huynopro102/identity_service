package identity.TuanHuy.dto.response;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
     String id;
     String username;
     LocalDate dob;
     String email;
     Set<String> Role;
     Set<String> Permission;
}
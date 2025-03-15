package identity.TuanHuy.dto.response;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
     String id;
     String username;
     LocalDate dob;
     String email;
     String profileImage;
     Boolean emailVerified;
     Set<String> roles;
     Set<String> permissions;
}
package identity.TuanHuy.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddRoleToUserRequest {
    @NotNull(message = "FIELD_NOT_NULL")
    private String roleName;

}

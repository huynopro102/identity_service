package identity.TuanHuy.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RemoveRoleFromUserRequest {
    @NotNull(message = "FIELD_NOT_NULL")
    private String OldRoleName;

    @NotNull(message = "FIELD_NOT_NULL")
    private String NewRoleName;
}

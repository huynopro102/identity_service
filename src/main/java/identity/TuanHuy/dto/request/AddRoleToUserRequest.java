package identity.TuanHuy.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleToUserRequest {

    @NotNull(message = "FIELD_NOT_NULL")
    private String roleName;

}

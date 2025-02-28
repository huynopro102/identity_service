package identity.TuanHuy.dto.response;


import identity.TuanHuy.entity.Permission;
import identity.TuanHuy.entity.Role;
import identity.TuanHuy.entity.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RolesResponse {
    @NotBlank(message = "ROLE_NAME_NOT_NULL")
    private RoleEnum name;

    @NotBlank(message = "ROLE_DESCRIPTION_NOT_NULL")
    private String description;

    private Set<String> permissions;

}

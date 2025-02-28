package identity.TuanHuy.dto.response;

import identity.TuanHuy.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class RoleWithPermissionsResponse {
    private String name;
    private String description;
    private List<PermissionResponse> permissions;
}

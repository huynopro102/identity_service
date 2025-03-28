package identity.TuanHuy.dto.response;

import identity.TuanHuy.dto.request.PermissionUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponse {
    private String name;
    private String code;
    private String description;
    private Boolean isActive;
}

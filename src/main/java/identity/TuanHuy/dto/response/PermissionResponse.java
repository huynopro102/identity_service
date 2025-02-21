package identity.TuanHuy.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissionResponse {
    private String name;
    private String code;
    private String description;
    private Boolean isActive;

    public PermissionResponse(String code, String name, String description, Boolean isActive) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }
}

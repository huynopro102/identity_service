package identity.TuanHuy.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionUpdateRequest {
    private String code;
    private String description;
    private Boolean isActive;
}

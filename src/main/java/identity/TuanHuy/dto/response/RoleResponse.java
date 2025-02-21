package identity.TuanHuy.dto.response;

import identity.TuanHuy.entity.Permission;
import identity.TuanHuy.entity.RoleEnum;
import identity.TuanHuy.validation.EnumConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RoleResponse {
    @NotBlank(message = "ROLE_NAME_INVALID")
    private String name;

    @NotEmpty(message = "ROLE_DESCRIPTION_INVALID")
    private String description;

    public RoleResponse(String name , String description){
        this.name = name;
        this.description = description;
    }
    public RoleResponse(){

    }
}

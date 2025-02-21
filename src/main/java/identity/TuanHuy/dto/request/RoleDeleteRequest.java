package identity.TuanHuy.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleDeleteRequest {

    @NotBlank(message = "ROLE_NAME_INVALID")
    private String name;

    public RoleDeleteRequest(String name){
        this.name = name;
    }

    public RoleDeleteRequest(){

    }
}

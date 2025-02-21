package identity.TuanHuy.dto.request;

import identity.TuanHuy.entity.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
@Data
public class RoleCreateRequest {

    @NotBlank(message = "ROLE_NAME_INVALID")
    @Schema(defaultValue = "USER")
    private String name;

    @NotBlank(message = "ROLE_DESCRIPTION_INVALID")
    @Schema(defaultValue = "role default when created account")
    private String description;

}

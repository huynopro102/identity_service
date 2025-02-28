package identity.TuanHuy.mapper;


import identity.TuanHuy.dto.response.PermissionResponse;
import identity.TuanHuy.entity.Permission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    List<PermissionResponse> toPermissionsResponse(List<Permission> permissions);

}

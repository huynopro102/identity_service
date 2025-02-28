package identity.TuanHuy.mapper;

import identity.TuanHuy.dto.request.PermissionUpdateRequest;
import identity.TuanHuy.dto.response.PermissionResponse;
import identity.TuanHuy.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

//    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    @Mapping(source = "name" , target = "name")
    @Mapping(source = "code" , target = "code")
    @Mapping(source = "description" , target = "description")
    @Mapping(source = "isActive" , target = "isActive" )
    PermissionResponse toPermissionResponse(Permission permission);

    List<PermissionResponse> toPermissionsResponse(List<Permission> permissions);


    @Mapping(source = "code",target = "code")
    @Mapping(source = "isActive",target = "isActive")
    @Mapping(source = "description",target = "description")
    PermissionUpdateRequest toPermissionUpdateRequest(Permission permission);

}

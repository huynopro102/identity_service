package identity.TuanHuy.service;
import identity.TuanHuy.dto.request.RoleCreateRequest;
import identity.TuanHuy.dto.request.RoleDeleteRequest;
import identity.TuanHuy.dto.response.PermissionResponse;
import identity.TuanHuy.dto.response.RoleResponse;
import identity.TuanHuy.dto.response.RoleWithPermissionsResponse;
import identity.TuanHuy.dto.response.RolesResponse;
import identity.TuanHuy.entity.Permission;
import identity.TuanHuy.entity.Role;
import identity.TuanHuy.entity.RoleEnum;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.mapper.PermissionMapper;
import identity.TuanHuy.mapper.RoleMapper;
import identity.TuanHuy.repository.RoleRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@Service
public class RoleService {


    private final RoleRepository  roleRepository;

    private final PermissionMapper permissionMapper;


    public RoleService(RoleRepository roleRepository, PermissionMapper permissionMapper){
        this.roleRepository = roleRepository;
        this.permissionMapper = permissionMapper;
    }


    public RoleWithPermissionsResponse roleWithPermissionsResponse(String nameRole){

//        case repository not return Object Optional , mean return String , Boolean
//        Role role = roleRepository.findByName(RoleEnum.valueOf(roleName.toUpperCase()))
//                .orElseThrow(() -> new AppException(ErrorCode.ROLE_USER_NOT_FOUND));
//
//        RoleWithPermissionsResponse response = new RoleWithPermissionsResponse();
//        response.setName(role.getName().toString());
//        response.setDescription(role.getDescription());
//
//        // Chuyển đổi Set<Permission> thành List<PermissionResponse>
//        if (role.getPermissions() != null && !role.getPermissions().isEmpty()) {
//            List<PermissionResponse> permissionResponses = role.getPermissions().stream()
//                    .map(permission -> new PermissionResponse(
//                            permission.getName(),
//                            permission.getCode(),
//                            permission.getDescription(),
//                            permission.getIsActive()
//                    ))
//                    .toList();
//
//            response.setPermissions(permissionResponses);
//        } else {
//            response.setPermissions(List.of()); // Trả về danh sách rỗng nếu không có permissions
//        }
//
//        return response;

//        case repository return Optional<Object>
        return roleRepository.findByName(RoleEnum.valueOf(nameRole.toUpperCase()))
                .map(role ->{

                    RoleWithPermissionsResponse roleWithPermissionsResponse = new RoleWithPermissionsResponse();

                    roleWithPermissionsResponse.setName(role.getName().toString());
                    roleWithPermissionsResponse.setDescription(role.getDescription().toString());

                    // Chuyển đổi Set<Permission> thành List<PermissionResponse>
                    List<PermissionResponse> permissionResponses = role.getPermissions() != null ?

                            role.getPermissions().stream() // from Set<Permission> to stream<permission>
                                    // lưu ý ko nên -> new Permission , vì Permission là 1 entity tức là 1 bảng
                                    .map(permission -> new PermissionResponse(
                                            permission.getName(),
                                            permission.getCode(),
                                            permission.getDescription(),
                                            permission.getIsActive()
                                    )).toList() : List.of() // List<permission>
                            ;

                    roleWithPermissionsResponse.setPermissions(permissionResponses);
                    return roleWithPermissionsResponse;

                })
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    public List<RolesResponse> getAllRoles(){
        return roleRepository.findAll()
                .stream()
                .map(item -> new RolesResponse(
                        item.getName(),
                        item.getDescription(),
                        item.getPermissions()
                                .stream()
                                .map(Permission::getName)
                                .collect(Collectors.toSet()) // Set<String>
                ))
                .toList();
        // case 1 : Stream
        // case 2 : Mapstruct + Stream
    }


    public RoleResponse createRole(RoleCreateRequest request){
     roleRepository.findByName(RoleEnum.valueOf(request.getName()))
                .ifPresent(role ->{
                    throw new AppException(ErrorCode.ROLE_NAME_ALREADY_EXISTS);
                });

        Role role = new Role();
        // convert to String from EnumRole
        role.setName(RoleEnum.valueOf(request.getName().toUpperCase()));
        role.setDescription(String.valueOf(request.getDescription()));

        roleRepository.save(role);

        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setName(String.valueOf(role.getName()));
        roleResponse.setDescription(role.getDescription());

        return roleResponse;
    }


    @Transactional
    public RoleResponse removeRole(RoleDeleteRequest request){
        // case normally
//            Optional<Role> roleEnum = roleRepository.findByName(RoleEnum.valueOf(request.getName()));
//            if(!roleEnum.isPresent()){
//               throw new AppException(ErrorCode.ROLE_USER_NOT_FOUND);
//            }
//            roleRepository.deleteByName(roleEnum.get().getName());
//            return new RoleResponse(String.valueOf(roleEnum.get().getName()),roleEnum.get().getDescription());

//    more optimal
        Role role = roleRepository.findByName(RoleEnum.valueOf(request.getName()))
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        roleRepository.deleteByName(role.getName());
        return new RoleResponse(String.valueOf(role.getName()),role.getDescription());
    }

}

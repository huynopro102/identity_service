package identity.TuanHuy.controller;
import identity.TuanHuy.dto.request.RoleCreateRequest;
import identity.TuanHuy.dto.request.RoleDeleteRequest;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.RoleResponse;
import identity.TuanHuy.dto.response.RoleWithPermissionsResponse;
import identity.TuanHuy.dto.response.RolesResponse;
import identity.TuanHuy.service.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping()
    ApiResponse<RoleResponse> CreateRole(@RequestBody @Valid RoleCreateRequest request) {
        RoleResponse roleResponse = roleService.createRole(request);
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .message("Role created successfully")
                .result(roleResponse)
                .build();
    }

    @GetMapping()
    ApiResponse<List<RolesResponse>> getAllRoles(){
        List<RolesResponse> listRoles = roleService.getAllRoles();
        return ApiResponse.<List<RolesResponse>>builder()
                .result(listRoles)
                .code(200)
                .message("get all role successfully")
                .build();
    }

    @DeleteMapping("/{roleName}")
    ApiResponse<RoleResponse> DeleteRoleByName(@PathVariable("roleName") String roleName){
        RoleResponse Role = roleService.removeRole(new RoleDeleteRequest(roleName));
        return ApiResponse.<RoleResponse>builder()
                .result(Role)
                .code(200)
                .message("delete role successfully")
                .build();
    }

    @GetMapping("/{roleName}/details")
    ApiResponse<RoleWithPermissionsResponse> GetRoleWithPermissions(@PathVariable String roleName){

        RoleWithPermissionsResponse roleWithPermissionsResponse = roleService.roleWithPermissionsResponse(roleName);

        return ApiResponse.<RoleWithPermissionsResponse>builder()
                .code(200)
                .message("get role with all permissions")
                .result(roleWithPermissionsResponse)
                .build()
                ;

    }


}

package identity.TuanHuy.controller;


import identity.TuanHuy.dto.request.PermissionRequest;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.PermissionResponse;
import identity.TuanHuy.service.PermissionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/api/permissions")
@CrossOrigin(origins = "*")

public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService){
        this.permissionService = permissionService;
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> GetAllPermissions(){
        List<PermissionResponse> listPermissions = permissionService.GetAllPermission();
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(200)
                .message("get all permissions successfully")
                .result(listPermissions)
                .build()
                ;
    }

    @GetMapping("/{namePermission}")
    public ApiResponse<PermissionResponse> GetPermissionByName(@PathVariable("namePermission") PermissionRequest request){
                PermissionResponse permissionResponse = permissionService.GetByName(request);
                return ApiResponse.<PermissionResponse>builder()
                        .code(200)
                        .message("get permission by Name successfully")
                        .result(permissionResponse)
                        .build()
                        ;
    }


}

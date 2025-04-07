package identity.TuanHuy.controller;
import identity.TuanHuy.dto.request.PermissionCreateRequest;
import identity.TuanHuy.dto.request.PermissionUpdateRequest;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.PermissionResponse;
import identity.TuanHuy.service.PermissionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/permissions")
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
    public ApiResponse<PermissionResponse> GetPermissionByName(@PathVariable("namePermission") String request){
                PermissionResponse permissionResponse = permissionService.GetByName(request);
                return ApiResponse.<PermissionResponse>builder()
                        .code(200)
                        .message("get permission by Name successfully")
                        .result(permissionResponse)
                        .build()
                        ;
    }

    @PutMapping("/{namePermission}")
    public ApiResponse<PermissionResponse> UpdatePermissionByName(@PathVariable("namePermission") String namePermission , @RequestBody PermissionUpdateRequest request){
        PermissionResponse permissionResponse = permissionService.UpdateByName(namePermission,request);

        return ApiResponse.<PermissionResponse>builder()
                .code(200)
                .message("update permission successfully")
                .result(permissionResponse)
                .build()
                ;
    }

    @PostMapping()
    public ApiResponse<PermissionResponse> CreatePermission(PermissionCreateRequest request){
        PermissionResponse permission = permissionService.CreatePermission(request);

        return ApiResponse.<PermissionResponse>builder()
                .result(permission)
                .code(200)
                .message("create permission successfully")
                .build()
                ;
    }

    @DeleteMapping("/{namePermission}")
    public ApiResponse<PermissionResponse> DeletePermission(@PathVariable String namePermission){
        PermissionResponse permission = permissionService.DeleteByName(namePermission);
        return ApiResponse.<PermissionResponse>builder()
                .message("delete permission successfully")
                .code(200)
                .result(permission)
                .build()
                ;
    }


}

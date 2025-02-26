package identity.TuanHuy.service;

import identity.TuanHuy.dto.request.PermissionRequest;
import identity.TuanHuy.dto.response.PermissionResponse;
import identity.TuanHuy.entity.Permission;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.mapper.PermissionMapper;
import identity.TuanHuy.repository.PermissionRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Builder
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public PermissionService(PermissionRepository permissionRepository , PermissionMapper permissionMapper){
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    public List<PermissionResponse> GetAllPermission(){

        List<Permission> listPermissions = permissionRepository.findAll();

        return permissionMapper.toPermissionsResponse(listPermissions);

    }

    public PermissionResponse GetByName(PermissionRequest request){
        Optional<Permission> optional = permissionRepository.findByName(request.getName());
                if(optional.isPresent()){
                    PermissionResponse permissionResponse = permissionMapper.toPermissionResponse(optional.get());
                    return permissionResponse;
                }else{
                    throw new AppException(ErrorCode.PERMISSION_NOT_FOUND);
                }
    }

}

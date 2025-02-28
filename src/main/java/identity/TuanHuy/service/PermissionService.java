package identity.TuanHuy.service;

import identity.TuanHuy.dto.request.PermissionCreateRequest;
import identity.TuanHuy.dto.request.PermissionRequest;
import identity.TuanHuy.dto.request.PermissionUpdateRequest;
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

    public PermissionResponse GetByName(String request){
        Optional<Permission> optional = permissionRepository.findByName(request);
                if(optional.isPresent()){
                    PermissionResponse permissionResponse = permissionMapper.toPermissionResponse(optional.get());
                    return permissionResponse;
                }else{
                    throw new AppException(ErrorCode.PERMISSION_NOT_FOUND);
                }
    }

    public PermissionResponse UpdateByName(String nameId , PermissionUpdateRequest request){
        Optional<Permission> permission = permissionRepository.findByName(nameId);
        if(permission.isPresent()){
                permission.get().setCode(request.getCode());
                permission.get().setDescription(request.getDescription());
                permission.get().setIsActive(request.getIsActive());

                permissionRepository.save(permission.get());

                return permissionMapper.toPermissionResponse(permission.get());
        }else{
            throw new AppException(ErrorCode.PERMISSION_NOT_FOUND);
        }
    }

    public PermissionResponse DeleteByName(String nameId){
        Optional<Permission> permission = permissionRepository.findByName(nameId);
        if(permission.isPresent()){
            permissionRepository.delete(permission.get());
            return permissionMapper.toPermissionResponse(permission.get());
        }else {
            throw new AppException(ErrorCode.PERMISSION_NOT_FOUND);
        }
    }

    public PermissionResponse CreatePermission(PermissionCreateRequest request){
            permissionRepository.findByName(request.getName().toUpperCase().trim())
                    .ifPresent(permission -> {
                        throw new AppException(ErrorCode.PERMISSION_READY_EXISTS);
                    });

            Permission permission = new Permission();

            permission.setName(request.getName().toUpperCase().trim());
            permission.setDescription(request.getDescription());
            permission.setCode(request.getCode());
            permission.setIsActive(request.getIsActive());

            permissionRepository.save(permission);

            return permissionMapper.toPermissionResponse(permission);
    }

}

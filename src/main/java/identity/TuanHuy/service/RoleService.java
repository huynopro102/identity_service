package identity.TuanHuy.service;
import identity.TuanHuy.dto.request.RoleCreateRequest;
import identity.TuanHuy.dto.request.RoleDeleteRequest;
import identity.TuanHuy.dto.response.RoleResponse;
import identity.TuanHuy.dto.response.RolesResponse;
import identity.TuanHuy.entity.Role;
import identity.TuanHuy.entity.RoleEnum;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.repository.RoleRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Data
@Getter
@Setter
@Service
public class RoleService {

    private final RoleRepository  roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public List<RolesResponse> getAllRoles(){
        return roleRepository.findAll()
                .stream()
                .map(item -> new RolesResponse(item.getName(),item.getDescription()))
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
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_USER_NOT_FOUND));
        roleRepository.deleteByName(role.getName());
        return new RoleResponse(String.valueOf(role.getName()),role.getDescription());
    }

}

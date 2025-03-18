package identity.TuanHuy.service;
import identity.TuanHuy.dto.request.AddRoleToUserRequest;
import identity.TuanHuy.dto.request.RemoveRoleFromUserRequest;
import identity.TuanHuy.dto.request.UserCreationRequest;
import identity.TuanHuy.dto.request.UserUpdateRequest;
import identity.TuanHuy.dto.response.UserResponse;
import identity.TuanHuy.entity.Role;
import identity.TuanHuy.entity.Users;
import identity.TuanHuy.entity.RoleEnum;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.mapper.UserMapper;
import identity.TuanHuy.repository.RoleRepository;
import identity.TuanHuy.repository.UserRepository;
import identity.TuanHuy.repository.UserRepositoryJDBC;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@EnableCaching // Bật cache
@Data
@Service
public class UserService {

    private final UserRepositoryJDBC userRepositoryJDBC ;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private RoleEnum roleEnum;

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    public UserService(UserRepositoryJDBC userRepositoryJDBC,UserRepository userRepository , UserMapper userMapper , RoleRepository roleRepository){
        this.userRepositoryJDBC = userRepositoryJDBC;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public int GetTotalUsers(){
        return userRepositoryJDBC.getTotalUsers();
    }

    public UserResponse removeRoleFromUser(String userId , RemoveRoleFromUserRequest request){
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Role Oldrole = roleRepository.findByName(RoleEnum.valueOf(request.getOldRoleName()))
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_OLD_NOT_FOUND));

        Role NewRole = roleRepository.findByName(RoleEnum.valueOf(request.getNewRoleName()))
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NEW_NOT_FOUND));


        // if role name doesn't into Set<Role>
        if(!user.getRoles().stream().anyMatch(r -> r.getName().equals(Oldrole.getName()))){
            throw new AppException(ErrorCode.ROLE_NAME_NOT_FOUND_IN_THIS_USER);
        }

        // get Set<Role> in user
        Set<Role> listRole = new HashSet<>(user.getRoles());

        listRole.remove(RoleEnum.valueOf(request.getOldRoleName()));
        listRole.add(NewRole);

        user.setRoles(listRole);

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    public UserResponse addRoleToUser(String userId , AddRoleToUserRequest roleName){

        Users user = userRepository.findById(userId)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));

        Role role = roleRepository.findByName(RoleEnum.valueOf(roleName.getRoleName().toUpperCase()))
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));


        if(user.getRoles().stream().anyMatch(r -> r.getName().equals(role.getName()))){
            System.out.println("role already assigned");
            throw new AppException(ErrorCode.ROLE_ALREADY_ASSIGNED);
        }

        // Thêm role vào set roles của user

        Set<Role> updatedRoles =  new HashSet<>(user.getRoles());

        updatedRoles.add(role);

        user.setRoles(updatedRoles);

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    public UserResponse CreateUser(UserCreationRequest request) {
                log.info("Service create User");
                userRepository.findByUsername(request.getUsername())
                        .ifPresent(user -> {
                            // thêm 1 thuộc tính cho exception này là ErrorCode lỗi lần bắt chỉ cần bắt , thuộc tính này là sẽ biết đc lỗi gì
                            throw new AppException(ErrorCode.USER_EXITED);
                        });

                // check role before create user
                Role roleuser = roleRepository.findByName(RoleEnum.USER)
                        .orElseThrow(()-> new AppException(ErrorCode.ROLE_NOT_FOUND));

                // Chuyển đổi request thành entity user
                Users user = userMapper.toUser(request);
                user.setPassword(passwordEncoder().encode(request.getPassword()));
                user.setRoles(Set.of(roleuser));

                userRepository.save(user);

                return userMapper.toUserResponse(user);
    }

    // Lấy user từ cache (nếu chưa có thì query database)
    @Cacheable(value = "users", key = "#id")
    public UserResponse GetUserById(String id) {
        System.out.println("Fetching user from DB..."); // Kiểm tra cache có hoạt động hay không
        Optional<Users> optional = userRepository.findById(id);
        if(optional.isPresent()){
            return userMapper.toUserResponse(optional.get());
        }else{
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
    }


    public List<UserResponse> GetAllUsers() {
        List<Users> listUser = userRepository.findAll();
        return userMapper.toUsersResponse(listUser);
    }



    public UserResponse UpdateUser(UserUpdateRequest resquest , String userId) {

        Users user = userRepository.findById(userId)
                        .orElseThrow(()-> new AppException(ErrorCode.USER_INVALID));

                        // Kiểm tra xem email có thuộc user khác không
                        userRepository.findByEmail(resquest.getEmail())
                                        .filter(existingUser -> !existingUser.getId().equals(userId))
                                .ifPresent(existingUser -> {
                                    throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
                                });


        userRepository.findByUsername(resquest.getUsername())
                .filter(existingUser -> !existingUser.getId().equals(userId)) // Kiểm tra tương tự với username
                .ifPresent(existingUser -> {
                    throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
                });


        userMapper.updateUser(user,resquest);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }


    // Xóa user khỏi cache khi user bị xóa
    @CacheEvict(value = "users", key = "#id")
    public String DeleteUser(String id) {
        Users user = userRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        userRepository.deleteById(id);
        return "User deleted successfully";
    }


}

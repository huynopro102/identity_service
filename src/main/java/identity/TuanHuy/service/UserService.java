package identity.TuanHuy.service;
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
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private RoleEnum roleEnum;

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    public UserService(UserRepository userRepository , UserMapper userMapper , RoleRepository roleRepository){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
                        .orElseThrow(()-> new AppException(ErrorCode.ROLE_USER_NOT_FOUND));

                // Chuyển đổi request thành entity user
                Users user = userMapper.toUser(request);
                user.setPassword(passwordEncoder().encode(request.getPassword()));
                user.setRoles(Set.of(roleuser));

                userRepository.save(user);

                return userMapper.toUserReponse(user);
    }

    public UserResponse GetUserById(String id) {
        Users users = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        return userMapper.toUserReponse(users);
    }

    public List<UserResponse> GetAllUsers() {
        List<Users> listUser = userRepository.findAll();
        return userMapper.toUsersReponse(listUser);
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
        return userMapper.toUserReponse(user);
    }

    public String DeleteUser(String id) {
        Users user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

}

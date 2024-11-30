package identity.TuanHuy.service;
import identity.TuanHuy.dto.request.UserCreationRequest;
import identity.TuanHuy.dto.request.UserUpdateRequest;
import identity.TuanHuy.entity.User;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public User CreateUser(UserCreationRequest request) {
                userRepository.findByUsername(request.getUsername())
                        .ifPresent(user -> {
                            // thêm 1 thuộc tính cho exceptin này là ErrorCode lỗi lần bắt chỉ cần bắt , thuộc tính này là sẽ biết đc lỗi gì
                            throw new AppException(ErrorCode.USER_EXITED);
                        });
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                User user = new User();
                user.setUsername(request.getUsername());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setDob(request.getDob());
                user.setEmail(request.getEmail());
                return userRepository.save(user);
    }

    public User GetUserById(String id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
    }

    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }

    public User UpdateUser(UserUpdateRequest request , String userId) {
        User user = userRepository.findById(userId)
                        .orElseThrow(()-> new RuntimeException(String.valueOf(ErrorCode.USER_NOT_FOUND)));
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setDob(request.getDob());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    public User DeleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        userRepository.deleteById(id);
        return user;
    }

}

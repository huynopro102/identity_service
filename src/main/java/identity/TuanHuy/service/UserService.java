package identity.TuanHuy.service;

import identity.TuanHuy.dto.request.UserCreationRequest;
import identity.TuanHuy.dto.request.UserUpdateRequest;
import identity.TuanHuy.entity.User;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User CreateUser(UserCreationRequest request) {
                User user = new User();
                if(userRepository.existsByUsername(request.getUsername())){
                    throw new AppException(ErrorCode.USER_EXITED);
                }
                user.setUsername(request.getUsername());
                user.setPassword(request.getPassword());
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
                        .orElseThrow(()-> new RuntimeException("user not found"));
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

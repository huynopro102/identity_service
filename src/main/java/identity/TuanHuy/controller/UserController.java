package identity.TuanHuy.controller;
import com.cloudinary.Api;
import identity.TuanHuy.dto.request.UserCreationRequest;
import identity.TuanHuy.dto.request.UserUpdateRequest;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.UserResponse;
import identity.TuanHuy.entity.Users;
import identity.TuanHuy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users") // Với cấu hình /api ở file .yaml , nó sẽ trở thành "/api/users"
@CrossOrigin(origins = "*") // Allow requests from the specified domain
public class UserController{

    @Autowired
    private UserService userService;

    @PostMapping()
    ApiResponse<UserResponse> CreateUser(@RequestBody @Valid UserCreationRequest request) {
        UserResponse userResponse = userService.CreateUser(request);
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("User created successfully")
                .result(userResponse)
                .build();
    }

    @GetMapping()
    ApiResponse<List<Users>> GetAllUsers() {
        List<Users> listUsers = userService.GetAllUsers();
        return ApiResponse.<List<Users>>builder()
                .message("get all users")
                .code(200)
                .result(listUsers)
                .build();

    }

    @GetMapping("/{userId}")
    ApiResponse<Users> GetUser(@PathVariable("userId") String userId) {
        Users user =  userService.GetUserById(userId);
        return ApiResponse.<Users>builder()
                .message("get user by id successfully")
                .code(200)
                .result(user)
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> UpdateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        UserResponse userResponse = userService.UpdateUser(request, userId);
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("User updated successfully")
                .result(userResponse)
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> DeleteUser(@PathVariable String userId) {
        return ApiResponse.<String>builder()
                .result(userService.DeleteUser(userId))
                .code(200)
                .message("user deleted successfully")
                .build();
    }

}

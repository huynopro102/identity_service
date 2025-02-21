package identity.TuanHuy.controller;
import com.cloudinary.Api;
import identity.TuanHuy.dto.request.UserCreationRequest;
import identity.TuanHuy.dto.request.UserUpdateRequest;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.UserResponse;
import identity.TuanHuy.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/api/users") // Với cấu hình /api ở file .yaml , nó sẽ trở thành "/api/users"
@CrossOrigin(origins = "*") // Allow requests from the specified domain
public class UserController{


    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

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
    ApiResponse<List<UserResponse>> GetAllUsers() {
        List<UserResponse> listUsers = userService.GetAllUsers();
        return ApiResponse.<List<UserResponse>>builder()
                .message("get all users")
                .code(200)
                .result(listUsers)
                .build();

    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> GetUser(@PathVariable("userId") String userId) {
        UserResponse userResponse =  userService.GetUserById(userId);
        return ApiResponse.<UserResponse>builder()
                .message("get user by id successfully")
                .code(200)
                .result(userResponse)
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

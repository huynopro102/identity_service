package identity.TuanHuy.controller;
import identity.TuanHuy.dto.request.AddRoleToUserRequest;
import identity.TuanHuy.dto.request.RemoveRoleFromUserRequest;
import identity.TuanHuy.dto.request.UserCreationRequest;
import identity.TuanHuy.dto.request.UserUpdateRequest;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.UserResponse;
import identity.TuanHuy.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/users") // Với cấu hình /api ở file .yaml , nó sẽ trở thành "/api/users"
@CrossOrigin(origins = "*") // Allow requests from the specified domain
public class UserController{

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PutMapping("/{userId}/role")
    ApiResponse<UserResponse> removeRoleFromUser(@PathVariable String userId , @RequestBody RemoveRoleFromUserRequest request){
        UserResponse userResponse = userService.removeRoleFromUser(userId,request);
        return ApiResponse.<UserResponse>builder()
                .message("updated role to user successfully")
                .code(200)
                .result(userResponse)
                .build()
                ;
    }

    @PostMapping("/{userId}/role")
    ApiResponse<UserResponse> addRoleToUser(@PathVariable String userId , @RequestBody AddRoleToUserRequest roleName){
        UserResponse userResponse = userService.addRoleToUser(userId,roleName);
            return ApiResponse.<UserResponse>builder()
                    .message("created role to user successfully")
                    .code(200)
                    .result(userResponse)
                    .build()
                    ;
    }

    @GetMapping("/total")
    ApiResponse<Integer> GetTotalUsers(){
       return ApiResponse.<Integer>builder()
                .result(userService.GetTotalUsers())
               .code(200)
               .message("Get Total Users successfully")
               .build()
               ;
    }

    @PostMapping()
    ApiResponse<UserResponse> CreateUser(@RequestBody @Valid UserCreationRequest request) {
        log.info("Controller : create user");
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
    private ApiResponse<String> DeleteUser(@PathVariable String userId) {
        return ApiResponse.<String>builder()
                .result(userService.DeleteUser(userId))
                .code(200)
                .message("user deleted successfully")
                .build();
    }


}

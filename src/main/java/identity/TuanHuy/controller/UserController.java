package identity.TuanHuy.controller;


import identity.TuanHuy.dto.reponse.ApiResponse;
import identity.TuanHuy.dto.request.UserCreationRequest;
import identity.TuanHuy.dto.request.UserUpdateRequest;
import identity.TuanHuy.entity.User;
import identity.TuanHuy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    User CreateUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.CreateUser(request);
    }

    @GetMapping()
    ApiResponse<List> GetAllUsers() {
        ApiResponse<List> response = ApiResponse.<List>builder()
                .code(200)
                .message("oke")
                .result(userService.GetAllUsers()).build();
        return response;
    }

    @GetMapping("/{userId}")
    User GetUser(@PathVariable("userId") String userId) {
        return userService.GetUserById(userId);
    }

    @PutMapping("/{userId}")
    User UpdateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.UpdateUser(request, userId);
    }

    @DeleteMapping("/{userId}")
    User DeleteUser(@PathVariable String userId) {
        return userService.DeleteUser(userId);
    }

}

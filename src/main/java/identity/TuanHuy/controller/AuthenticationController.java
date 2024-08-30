package identity.TuanHuy.controller;

import identity.TuanHuy.dto.reponse.ApiResponse;
import identity.TuanHuy.dto.reponse.AuthenticationReponse;
import identity.TuanHuy.dto.request.AuthenticationRequest;
import identity.TuanHuy.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationReponse> login(@RequestBody AuthenticationRequest request) {
        Boolean result = authenticationService.authenticate(request);
        // .<1 đối tượng hoặc 1 kiểu dữ liệu>
        return ApiResponse.<AuthenticationReponse>builder()
                .code(200)
                .message("oke")
                .result(AuthenticationReponse.builder().authenticated(result).build())
                .build();
    }

}

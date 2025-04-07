package identity.TuanHuy.controller;
import com.nimbusds.jose.JOSEException;
import identity.TuanHuy.dto.response.ApiResponse;
import identity.TuanHuy.dto.response.AuthenticationResponse;
import identity.TuanHuy.dto.response.IntrospectResponse;
import identity.TuanHuy.dto.request.AuthenticationRequest;
import identity.TuanHuy.dto.request.IntrospectRequest;
import identity.TuanHuy.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController  {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> login(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        // .<1 đối tượng hoặc 1 kiểu dữ liệu>
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/me")
    public ApiResponse<IntrospectResponse> me(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        IntrospectResponse result = authenticationService.introspect(request);
        if (result.getValid()==true){

            return ApiResponse.<IntrospectResponse>builder()
                    .code(200)
                    .message("success")
                    .result(result)
                    .build()
                    ;
        }else{
            return ApiResponse.<IntrospectResponse>builder()
                    .code(404)
                    .message("fail")
                    .build()
                    ;
        }
    }
}

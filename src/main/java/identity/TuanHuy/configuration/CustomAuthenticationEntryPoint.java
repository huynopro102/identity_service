package identity.TuanHuy.configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Xác định lỗi xác thực cụ thể
        String errorMessage = "Unauthorized access: You need to login first";
        if (authException.getMessage().contains("Bad credentials")) {
            errorMessage = "Invalid username or password";
        } else if (authException.getMessage().contains("User is disabled")) {
            errorMessage = "Your account is disabled";
        } else if (authException.getMessage().contains("User account is locked")) {
            errorMessage = "Your account is locked";
        } else if (authException.getMessage().contains("Token expired")) {
            errorMessage = "Your session has expired, please login again";
        }

        // Tạo phản hồi JSON
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        data.put("message", errorMessage);
        data.put("path", request.getRequestURI());
        data.put("error", "Unauthorized");

        response.getWriter().write(objectMapper.writeValueAsString(data));
    }
}

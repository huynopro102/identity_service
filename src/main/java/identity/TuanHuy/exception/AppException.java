package identity.TuanHuy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;

@AllArgsConstructor
@Data
public class AppException extends RuntimeException{
    private ErrorCode errorCode;
}
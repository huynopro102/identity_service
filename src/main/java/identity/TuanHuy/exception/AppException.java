package identity.TuanHuy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppException extends RuntimeException{
    private ErrorCode errorCode;
}
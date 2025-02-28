package identity.TuanHuy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@AllArgsConstructor
@Data
public class AppException extends RuntimeException{
    private ErrorCode errorCode;
}
package identity.TuanHuy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"UNCATEGORIZED_EXCEPTION"),
    USER_EXITED(1001,"Username already exists"),
    USER_INVALID(1002,"username at least 4 charactor")
    ;
    private int code;
    private String message;
}

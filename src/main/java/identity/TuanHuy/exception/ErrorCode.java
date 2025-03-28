package identity.TuanHuy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INVALID_KEY_ENUM(1000, "KEY IN ENUM INVALID", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXITED(1001, "Username already exists", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1003, "User not found", HttpStatus.NOT_FOUND),
    USER_INVALID(1002, "Username must be at least 4 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1005, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    USERNAME_NOT_NULL(1003, "Username must not be null", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_NULL(1004, "Password must not be null", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_NULL(1006, "Email must not be null", HttpStatus.BAD_REQUEST),
    DOB_NOT_NULL(1007, "Date of birth must not be null", HttpStatus.BAD_REQUEST),
    INVALID_DATE_FORMAT(1008, "Invalid date format, use year-month-day (e.g., 2003-04-12)", HttpStatus.BAD_REQUEST),
    PATH_REQUEST_INVALID(1009, "Invalid request path", HttpStatus.NOT_FOUND),
    AUTHENTICATE_INVALID(1010, "Invalid authentication", HttpStatus.UNAUTHORIZED),
    GENRE_EXITED(1011, "Genre already exists", HttpStatus.BAD_REQUEST),
    POST_CATEGORY_NOT_EMPTY(1012, "Post category must not be empty", HttpStatus.BAD_REQUEST),
    POST_NOT_FOUND(1013, "Post ID not found", HttpStatus.NOT_FOUND),
    GENRE_NOT_FOUND(1014, "Genre ID not found", HttpStatus.NOT_FOUND),
    INVALID_DOB(1015, "Age must be >= 18", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS(1016, "Email already exists", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1017, "Invalid email format", HttpStatus.BAD_REQUEST),
    ROLE_NAME_INVALID(1018, "Role name invalid", HttpStatus.BAD_REQUEST),
    ROLE_DESCRIPTION_INVALID(1019, "Role description invalid", HttpStatus.BAD_REQUEST),
    ROLE_NAME_ALREADY_EXISTS(1020, "Role name already exists", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_ACCESS(1021, "AppException doesn't catch this exception", HttpStatus.UNAUTHORIZED) ,
    ROLE_NOT_FOUND(1022, "role name không tồn tại trong danh sách ", HttpStatus.BAD_REQUEST),
    DATA_TYPE_ROLE_ENUM_NOT_FOUND(1023, "role name không tồn tại trong danh sách", HttpStatus.BAD_REQUEST),
    INVALID_JSON_FORMAT(1024, "format của kiểu dữ liệu json trong body bị sai",HttpStatus.BAD_REQUEST) ,
    ROLE_IN_USE(1025,"Cannot delete role as it is assigned to users",HttpStatus.BAD_REQUEST) ,
    USERNAME_ALREADY_EXISTS(1026, "this username is another people used", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_FOUND(1027, "not found this name permission", HttpStatus.BAD_REQUEST),
    PERMISSION_READY_EXISTS(1028, "permission ready exists", HttpStatus.BAD_REQUEST),
    ROLE_ALREADY_ASSIGNED(1029,"role this assign name with this role", HttpStatus.BAD_REQUEST) ,
    ROLE_NAME_NOT_NULL(1030,"field role name ís not null", HttpStatus.BAD_REQUEST ),
    FIELD_NOT_NULL(1031,"field not null",HttpStatus.BAD_REQUEST) ,
    ROLE_NAME_NOT_FOUND_IN_THIS_USER(1030,"ROLE_NAME_NOT_FOUND_IN_THIS_USER", HttpStatus.BAD_REQUEST ),
    ROLE_OLD_NOT_FOUND(1031,"role name old not found", HttpStatus.BAD_REQUEST) ,
    ROLE_NEW_NOT_FOUND(1032,"role name old not found", HttpStatus.BAD_REQUEST) ,
    SIGNER_KEY_IS_TOO_SHORT(1033,"SIGNER_KEY phải có ít nhất 32 ký tự.", HttpStatus.BAD_REQUEST) ,
    SIGNER_KEY_ERROR(1034,"SIGNER_KEY bij loi", HttpStatus.BAD_REQUEST) ,
    GENERATE_TOKEN_FAILED(1035,"Không thể tạo token.", HttpStatus.BAD_REQUEST),
    DATABASE_CONNECTION(1036,"wating a minutes , DatabaseConnection is not initialized.", HttpStatus.BAD_REQUEST),
    PODCAST_NAME_EXISTS(1037,"tên của cuốn sách này đã đc đặt", HttpStatus.BAD_REQUEST),
    PODCAST_NOT_EXISTS(1037,"id podcast không tồn tại", HttpStatus.BAD_REQUEST),

    ;

    // Modifier 'public' not allowed here
    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;
}

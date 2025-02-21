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
    UNAUTHORIZED_ACCESS(1021, "spring security Unauthorized access", HttpStatus.UNAUTHORIZED) ,
    ROLE_USER_NOT_FOUND(1022, "role name không tồn tại trong danh sách ", HttpStatus.BAD_REQUEST),
    DATA_TYPE_ROLE_ENUM_NOT_FOUND(1023, "role name không tồn tại trong danh sách", HttpStatus.BAD_REQUEST),
    INVALID_JSON_FORMAT(1024, "format của kiểu dữ liệu json trong body bị sai",HttpStatus.BAD_REQUEST) ,
    ROLE_IN_USE(1025,"Cannot delete role as it is assigned to users",HttpStatus.BAD_REQUEST)



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

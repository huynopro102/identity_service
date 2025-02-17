
package identity.TuanHuy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor

public enum ErrorCode {
    INVALID_KEY_ENUM(1000,"KEY IN ENUM INVALID"),
    UNCATEGORIZED_EXCEPTION(9999,"UNCATEGORIZED_EXCEPTION"),
    USER_EXITED(1001,"Username already exists"),
    USER_NOT_FOUND(1003,"User not found"),
    USER_INVALID(1002,"username at least 4 charactor"),
    PASSWORD_INVALID(1005,"password at least 8 charactor"),
    USERNAME_NOT_NULL(1003,"username not null"),
    PASSWORD_NOT_NULL(1004,"password not null"),
    EMAIL_NOT_NULL(1006,"email not null"),
    DOB_NOT_NULL(1007,"dob not null"),
    INVALID_DATE_FORMAT(1008,"Invalid date format , format year-month-day , example for: 2003-04-12 ,There must be a 0 before the numbers 1-9"),
    PATH_REQUEST_INVALID(1009,"path request invalid , đường đẫn gửi request sai"),
    AUTHENTICATE_INVALID(1010,"authenticate invalid"),
    GENRE_EXITED(1011,"genre is already exists"),
    POST_CATEGORY_NOT_EMPTY(1012,"Tên danh mục không được để trống") ,
    POST_NOT_FOUND(1013,"id của bài post không tồn tại") ,
    GENRE_NOT_FOUND(1014,"id của genre không tồn tại") ,
    INVALID_DOB(1015,"AGE >= 18") ,
    EMAIL_ALREADY_EXISTS(1016,"email already exists") ,
    EMAIL_INVALID(1017,"email invalid") ,
    ;
    private int code;
    private String message;
}

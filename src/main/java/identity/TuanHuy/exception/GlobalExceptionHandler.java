package identity.TuanHuy.exception;
import com.cloudinary.Api;
import com.nimbusds.jose.KeyLengthException;
import identity.TuanHuy.dto.response.ApiResponse;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice // Add this annotation to enable global exception handling , thêm annotation này để xử lý toàn cục
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Object>> handlingValidationException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//        List<ApiResponse<Void>> apiResponses = new ArrayList<>();

        for (int i = 0; i < fieldErrors.size(); i++) {
            log.info("this is a log info : "+fieldErrors.get(i).toString());
        }
            log.info("this is a log info size : " + fieldErrors.size());

            ErrorCode errorCode =  ErrorCode.valueOf(e.getBindingResult().getFieldError().getDefaultMessage());


            ApiResponse<Object> apiResponse = ApiResponse.builder()
                    .message(errorCode.getMessage())
                    .code(errorCode.getCode())
                    .build();



//        for (FieldError fieldError : fieldErrors) {
//            ErrorCode errorCode;
//            try {
//                String enumkey = fieldError.getDefaultMessage();
//                errorCode = ErrorCode.valueOf(enumkey);
//            } catch (IllegalArgumentException exception) {
//                // Nếu không tìm thấy enum key hợp lệ, sử dụng INVALID_KEY_ENUM
//                errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
//            }
//            ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
//                    .message(errorCode.getMessage())
//                    .code(errorCode.getCode())
//                    .build();
//            apiResponses.add(apiResponse);
//        }

        return ResponseEntity.badRequest().body(apiResponse);
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    ResponseEntity<ApiResponse> handleHttpMessageNotReadableExceptionDOB(HttpMessageNotReadableException e) {

        ApiResponse.ApiResponseBuilder responseBuilder = ApiResponse.builder();

                    if(e.getMessage().contains("Unexpected character")){
                        responseBuilder
                                .code(ErrorCode.INVALID_JSON_FORMAT.getCode())
                                .message(ErrorCode.INVALID_JSON_FORMAT.getMessage());
                    }else{
                        responseBuilder
                                .code(ErrorCode.INVALID_DOB.getCode())
                                .message(ErrorCode.INVALID_DOB.getMessage());
                    }
        return ResponseEntity.badRequest().body(responseBuilder.build());
    }
    @ExceptionHandler(value = KeyLengthException.class)
    ResponseEntity<ApiResponse> handleKeyLengthException(KeyLengthException e){
        ApiResponse.ApiResponseBuilder responseBuilder = ApiResponse.builder();
        if(e.getMessage().equals("The secret length must be at least 256 bits")){
            responseBuilder
                    .code(ErrorCode.SIGNER_KEY_IS_TOO_SHORT.getCode())
                    .message(ErrorCode.SIGNER_KEY_IS_TOO_SHORT.getMessage())
                    .build()
                    ;
        }else{
            responseBuilder
                    .code(ErrorCode.SIGNER_KEY_ERROR.getCode())
                    .message(ErrorCode.SIGNER_KEY_ERROR.getMessage())
                    .build()
            ;
        }
        return ResponseEntity.badRequest().body(responseBuilder.build());
    }


    // kiểu dữ liệu enumRole ko có tên đó
    @ExceptionHandler(value = IllegalArgumentException.class)
    ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e){
        ApiResponse apiResponse = ApiResponse.builder()
                .code(ErrorCode.DATA_TYPE_ROLE_ENUM_NOT_FOUND.getCode())
                .message(ErrorCode.DATA_TYPE_ROLE_ENUM_NOT_FOUND.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }




    // lỗi 401 đến từ Spring Security , cần xử lý AuthenticationException:
    @ExceptionHandler(value = AuthenticationException.class)
    ResponseEntity<ApiResponse> handleAuthenticationException(AuthenticationException e) {

        ApiResponse.ApiResponseBuilder apiResponseBuilder = ApiResponse.builder();

                if(e.getMessage().contains("User not found")){
                    apiResponseBuilder
                            .code(ErrorCode.USER_NOT_FOUND.getCode())
                            .message(ErrorCode.USER_NOT_FOUND.getMessage())
                            ;
                }else{
                    apiResponseBuilder
                            .code(ErrorCode.UNAUTHORIZED_ACCESS.getCode())
                            .message(ErrorCode.UNAUTHORIZED_ACCESS.getMessage())
                            ;
                }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseBuilder.build());
    }




    @ExceptionHandler(value = HttpClientErrorException.Unauthorized.class)
    ResponseEntity<ApiResponse> handleUnauthorizedException(HttpClientErrorException.Unauthorized e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(ErrorCode.UNAUTHORIZED_ACCESS.getCode())
                .message(ErrorCode.UNAUTHORIZED_ACCESS.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }


    // customer exception
    // exception custom main
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleRuntimeExceptionMyapp(AppException e) {

        ApiResponse.ApiResponseBuilder apiResponseBuilder = ApiResponse.builder();

        System.out.println("this is message in AppException.class" + e.getMessage());

        if(e.getErrorCode() == ErrorCode.USER_NOT_FOUND){
            apiResponseBuilder
                    .code(ErrorCode.USER_NOT_FOUND.getCode())
                    .message(ErrorCode.USER_NOT_FOUND.getMessage())
            ;
        }else if(e.getErrorCode() == ErrorCode.PERMISSION_NOT_FOUND){
            apiResponseBuilder
                    .code(ErrorCode.PERMISSION_NOT_FOUND.getCode())
                    .message(ErrorCode.PERMISSION_NOT_FOUND.getMessage())
                    ;
        } else if(e.getErrorCode().equals(ErrorCode.PERMISSION_READY_EXISTS)){
            apiResponseBuilder
                    .code(ErrorCode.PERMISSION_READY_EXISTS.getCode())
                    .message(ErrorCode.PERMISSION_READY_EXISTS.getMessage())
                    ;
        } else if(e.getErrorCode().equals(ErrorCode.ROLE_NAME_ALREADY_EXISTS)){
            apiResponseBuilder
                    .message(ErrorCode.ROLE_NAME_ALREADY_EXISTS.getMessage())
                    .code(ErrorCode.ROLE_NAME_ALREADY_EXISTS.getCode())
                    ;
        } else if(e.getErrorCode().equals(ErrorCode.ROLE_NOT_FOUND)){
            apiResponseBuilder
                    .message(ErrorCode.ROLE_NOT_FOUND.getMessage())
                    .code(ErrorCode.ROLE_NOT_FOUND.getCode())
                    ;
        } else if(e.getErrorCode().equals(ErrorCode.ROLE_ALREADY_ASSIGNED)){
            apiResponseBuilder
                    .message(ErrorCode.ROLE_ALREADY_ASSIGNED.getMessage())
                    .code(ErrorCode.ROLE_ALREADY_ASSIGNED.getCode())
            ;
        } else if(e.getErrorCode().equals(ErrorCode.ROLE_NAME_NOT_FOUND_IN_THIS_USER)){
            apiResponseBuilder
                    .message(ErrorCode.ROLE_NAME_NOT_FOUND_IN_THIS_USER.getMessage())
                    .code(ErrorCode.ROLE_NAME_NOT_FOUND_IN_THIS_USER.getCode())
                    ;
        }  else if(e.getErrorCode().equals(ErrorCode.ROLE_NEW_NOT_FOUND)){
            apiResponseBuilder
                    .message(ErrorCode.ROLE_NEW_NOT_FOUND.getMessage())
                    .code(ErrorCode.ROLE_NEW_NOT_FOUND.getCode())
            ;
        }   else if(e.getErrorCode().equals(ErrorCode.ROLE_OLD_NOT_FOUND)){
            apiResponseBuilder
                    .message(ErrorCode.ROLE_OLD_NOT_FOUND.getMessage())
                    .code(ErrorCode.ROLE_OLD_NOT_FOUND.getCode())
            ;
        } else if(e.getErrorCode().equals(ErrorCode.SIGNER_KEY_IS_TOO_SHORT)){
            apiResponseBuilder
                    .message(ErrorCode.SIGNER_KEY_IS_TOO_SHORT.getMessage())
                    .code(ErrorCode.SIGNER_KEY_IS_TOO_SHORT.getCode())
                    ;
        }else if(e.getErrorCode().equals(ErrorCode.SIGNER_KEY_ERROR)){
            apiResponseBuilder
                    .message(ErrorCode.SIGNER_KEY_ERROR.getMessage())
                    .code(ErrorCode.SIGNER_KEY_ERROR.getCode())
            ;
        }else if(e.getErrorCode().equals(ErrorCode.DATABASE_CONNECTION)){
            apiResponseBuilder
                    .message(ErrorCode.DATABASE_CONNECTION.getMessage())
                    .code(ErrorCode.DATABASE_CONNECTION.getCode())
                    ;
        }else if(e.getErrorCode().equals(ErrorCode.PODCAST_NAME_EXISTS)){
            apiResponseBuilder
                    .message(ErrorCode.PODCAST_NAME_EXISTS.getMessage())
                    .code(ErrorCode.PODCAST_NAME_EXISTS.getCode())
            ;
        }else if(e.getErrorCode().equals(ErrorCode.PODCAST_NOT_EXISTS)){
            apiResponseBuilder
                    .message(ErrorCode.PODCAST_NOT_EXISTS.getMessage())
                    .code(ErrorCode.PODCAST_NOT_EXISTS.getCode())
            ;
        }else if (e.getErrorCode().equals(ErrorCode.USER_EXITED)){
            apiResponseBuilder
                    .message(ErrorCode.USER_EXITED.getMessage())
                    .code(ErrorCode.USER_EXITED.getCode())
                    ;
        }else if(e.getErrorCode().equals(ErrorCode.PODCAST_TITLE_ALREADY_EXITS)){
            apiResponseBuilder
                    .message(ErrorCode.PODCAST_TITLE_ALREADY_EXITS.getMessage())
                    .code(ErrorCode.PODCAST_TITLE_ALREADY_EXITS.getCode())
                    ;
        }else if(e.getErrorCode().equals(ErrorCode.EPISODE_IS_NULL)){
            apiResponseBuilder
                    .message(ErrorCode.EPISODE_IS_NULL.getMessage())
                    .code(ErrorCode.EPISODE_IS_NULL.getCode())
                    ;
        }else if(e.getErrorCode().equals(ErrorCode.THIS_EPISODE_ALREADY_EXIST)){
            apiResponseBuilder
                    .message(ErrorCode.THIS_EPISODE_ALREADY_EXIST.getMessage())
                    .code(ErrorCode.THIS_EPISODE_ALREADY_EXIST.getCode())
                    ;
        }


        else{
            apiResponseBuilder
                    .code(ErrorCode.UNAUTHORIZED_ACCESS.getCode())
                    .message(ErrorCode.UNAUTHORIZED_ACCESS.getMessage())
            ;
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseBuilder.build());
    }


    // lỗi này là sai đường dẫn url khi gửi request
    @ExceptionHandler(value = NoResourceFoundException.class)
    ResponseEntity<ApiResponse> handleNoResultException(NoResourceFoundException e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(ErrorCode.PATH_REQUEST_INVALID.getCode())
                .message(ErrorCode.PATH_REQUEST_INVALID.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }


    // Bắt email ko tìm thấy trong database
    @ExceptionHandler(value = NullPointerException.class)
    ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(ErrorCode.USER_NOT_FOUND.getCode())
                .message(ErrorCode.USER_NOT_FOUND.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }


    // exception email already exists
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception){

        ApiResponse.ApiResponseBuilder responseBuilder = ApiResponse.builder();

        String message = exception.getMostSpecificCause().getMessage();

        if(message.contains("violates foreign key constraint") && message.contains("user_roles")){
            responseBuilder
                    .code(ErrorCode.ROLE_IN_USE.getCode())
                    .message(ErrorCode.ROLE_IN_USE.getMessage());
        } else if(message.contains("duplicate key") && message.contains("email")){
            responseBuilder
                    .code(ErrorCode.EMAIL_ALREADY_EXISTS.getCode())
                    .message(ErrorCode.EMAIL_ALREADY_EXISTS.getMessage());
        }else{

            responseBuilder
                    .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                    .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        }

        return ResponseEntity.badRequest().body(responseBuilder.build());
    }



//    @ExceptionHandler(value = Exception.class)
//    ResponseEntity<ApiResponse> handleExceptionGlobal(Exception e) {
//        ApiResponse apiResponse = ApiResponse.builder()
//                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
//                .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
//                .build();
//        return ResponseEntity.badRequest().body(apiResponse);
//    }



}
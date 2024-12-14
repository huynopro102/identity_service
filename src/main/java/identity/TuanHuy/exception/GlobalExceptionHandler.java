package identity.TuanHuy.exception;

import identity.TuanHuy.dto.reponse.ApiResponse;
import jakarta.persistence.NoResultException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;


import java.util.ArrayList;
import java.util.List;


@ControllerAdvice(basePackages = "identity.TuanHuy/")
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<List<ApiResponse<Void>>> handllingValidationException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<ApiResponse<Void>> apiResponses = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            ErrorCode errorCode;
            try {
                String enumkey = fieldError.getDefaultMessage();
                errorCode = ErrorCode.valueOf(enumkey);
            } catch (IllegalArgumentException exception) {
                // Nếu không tìm thấy enum key hợp lệ, sử dụng INVALID_KEY_ENUM
                errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
            }
            ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                    .message(errorCode.getMessage())
                    .code(errorCode.getCode())
                    .build();
            apiResponses.add(apiResponse);
        }
        return ResponseEntity.badRequest().body(apiResponses);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    ResponseEntity<ApiResponse> handleHttpMessageNotReadableExceptionDOB(HttpMessageNotReadableException e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .message(ErrorCode.INVALID_DATE_FORMAT.getMessage())
                .code(ErrorCode.INVALID_DATE_FORMAT.getCode())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }


    // validation
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleRuntimeExceptionMyapp(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        System.out.println(e.getErrorCode().getMessage());
        ApiResponse apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
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




    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleExceptionGlobal(Exception e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }


}
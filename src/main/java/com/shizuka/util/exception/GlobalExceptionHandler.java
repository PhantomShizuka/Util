package com.shizuka.util.exception;

import com.shizuka.util.error.FileError;
import com.shizuka.util.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ApiResponse<?> handleFileException(ServiceException ex) {
        return ApiResponse.error(ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(BindingException.class)
    public ApiResponse<?> handleBindingException(BindingException ex) {
        return ApiResponse.error(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGlobalException(Exception ex) {
        return ApiResponse.error(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ApiResponse<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        return handleFileException(new ServiceException(FileError.SIZE, ex));
    }
}

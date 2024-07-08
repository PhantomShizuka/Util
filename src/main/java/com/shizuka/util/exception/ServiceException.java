package com.shizuka.util.exception;

import com.shizuka.util.error.IErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {
    private final HttpStatus status;

    public ServiceException(IErrorCode error) {
        super(error.getMessage());
        this.status = error.getStatus();
    }

    public ServiceException(IErrorCode error, Throwable cause) {
        super(error.getMessage(), cause);
        this.status = error.getStatus();
    }
}
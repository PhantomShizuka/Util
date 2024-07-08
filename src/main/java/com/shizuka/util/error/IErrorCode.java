package com.shizuka.util.error;

import org.springframework.http.HttpStatus;

public interface IErrorCode {
    String getMessage();
    HttpStatus getStatus();
}

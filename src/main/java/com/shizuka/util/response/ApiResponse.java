package com.shizuka.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private HttpStatus status;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, HttpStatus.OK);
    }

    public static <T> ApiResponse<T> error(T errorData, HttpStatus status) {
        return new ApiResponse<>(false, errorData, status);
    }
}
package com.shizuka.util.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BindingException extends RuntimeException {
    public List<String> errors;

    public BindingException(BindingResult bindingResult) {
        List<ObjectError> objectErrors = bindingResult.getAllErrors();

        errors = new ArrayList<>();

        for (ObjectError error : objectErrors) {
            errors.add(error.getDefaultMessage());
        }
    }
}
package com.HNG_UserAuth.advice;


import com.HNG_UserAuth.responseDto.UserValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<UserValidationErrorDto> handleValidationExceptions(MethodArgumentNotValidException exception) {

        List<UserValidationErrorDto.ErrorResponseDto> errors = new ArrayList<>();

        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(new UserValidationErrorDto.ErrorResponseDto(error.getField(), error.getDefaultMessage()));
        }

        UserValidationErrorDto userValidationErrorDto = new UserValidationErrorDto((UserValidationErrorDto.ErrorResponseDto) errors);

        return new ResponseEntity<UserValidationErrorDto>(userValidationErrorDto, HttpStatus.UNPROCESSABLE_ENTITY);

    }
}

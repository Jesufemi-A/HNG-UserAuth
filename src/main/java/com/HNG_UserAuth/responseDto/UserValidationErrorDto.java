package com.HNG_UserAuth.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
public class UserValidationDto {

    @Getter
    @Setter
    private List<ErrorDto> errors;

    public static class ErrorDto{

        @Getter
        @Setter
        private String field;

        @Getter
        @Setter
        private String message;
    }



}

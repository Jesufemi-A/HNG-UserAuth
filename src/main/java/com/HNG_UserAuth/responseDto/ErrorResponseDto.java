package com.HNG_UserAuth.responseDto;


import lombok.Data;

@Data
public class ErrorResponseDto {

    private String field;
    private String message;


    public ErrorResponseDto(String field, String message) {
        this.field = field;
        this.message = message;

    }
}

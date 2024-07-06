package com.HNG_UserAuth.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationErrorResponseDto {

    private String status;
    private String message;
    private int statusCode;
}

package com.HNG_UserAuth.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserValidationErrorDto {

    private ErrorResponseDto errors;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
   public static class ErrorResponseDto {

       private String field;
       private String message;
   }

}

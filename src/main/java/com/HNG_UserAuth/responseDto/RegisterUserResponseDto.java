package com.HNG_UserAuth.responseDto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserResponseDto {

    private String status;
    private String message;
    private Data data;

    public RegisterUserResponseDto(String status, String message, Data data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @lombok.Data
    public static class Data {
        private String accessToken;
        private User user;


        @lombok.Data
        public static class User {
            private String userId;
            private String firstName;
            private String lastName;
            private String email;
            private String phone;
        }


    }


}

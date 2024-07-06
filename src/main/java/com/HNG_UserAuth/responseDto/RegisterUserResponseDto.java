package com.HNG_UserAuth.responseDto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterUserDto {

    private int status;
    private String message;
    private Data data;

    public static class Data{
        private String accessToken;
        private User user;

        public static class User{
            private String userId;
            private String firstName;
            private String lastName;
            private String email;
            private String phone;
        }



    }

//    public RegisterUserDto(int status, String message){
//        this.status = status;
//        this.message = message;
//    }

}

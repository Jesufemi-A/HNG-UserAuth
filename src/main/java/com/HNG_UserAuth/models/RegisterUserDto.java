package com.HNG_UserAuth.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class RegisterUserDto {

    @NotNull
    @Size(min = 1, message = "First name must not be empty")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "LastName must not be empty")
    private String lastName;

    @NotNull
    @Email
    @Column(unique = true)
    @Email
    private String email;


    @NotNull
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;


    @NotNull
    private String phone;
}

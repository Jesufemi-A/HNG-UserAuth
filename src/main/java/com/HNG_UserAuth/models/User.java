package com.HNG_UserAuth.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table( name ="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String userId;

    @NotNull
    @Size(min=1, message = "First name must not be empty")
    private String firstName;

    @NotNull
    @Size(min=1, message = "LastName must not be empty")
    private String lastName;

    @NotNull
    @Email
    @Column(unique = true)
    private  String email;

    @NotNull
    private String phone;





}

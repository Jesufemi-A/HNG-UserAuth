package com.HNG_UserAuth.controllers;


import com.HNG_UserAuth.models.UserModel;
import com.HNG_UserAuth.responseDto.RegisterUserResponseDto;
import com.HNG_UserAuth.responseDto.UserRegistrationDto;
import com.HNG_UserAuth.services.JwtProviderService;
import com.HNG_UserAuth.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {


    private final UserService userService;
    private final JwtProviderService jwtProviderService;

    @Autowired
    public UserController(UserService userService, JwtProviderService jwtProviderService) {
        this.userService = userService;
        this.jwtProviderService = jwtProviderService;
    }


    @PostMapping("auth/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {

        UserModel user = new UserModel();
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setPassword(userRegistrationDto.getPassword());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPhone(userRegistrationDto.getPhone());

        UserModel registeredUser = userService.registerUser(user);


        String accesstoken = jwtProviderService.generateToken(registeredUser);
        var userDto = new RegisterUserResponseDto.Data.User();
        userDto.setFirstName(registeredUser.getFirstName());
        userDto.setLastName(registeredUser.getLastName());
        userDto.setEmail(registeredUser.getEmail());
        userDto.setUserId(registeredUser.getUserId());
        userDto.setPhone(registeredUser.getPhone());

        var data = new RegisterUserResponseDto.Data();
        data.setAccessToken(accesstoken);
        data.setUser(userDto);


        var registerUserDto = new RegisterUserResponseDto(201,
                "User registration successful", data);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUserDto);

    }

//    @PostMapping("/auth/login")
//    public void login() {
//
//    }
//
//    @GetMapping("/api/organisation")
//    public String getOrganisation() {
//        return "";
//    }
//
//    @GetMapping("/api/organisation/:orgid")
//    public String getOrganisationId(String id) {
//        return "";
//    }
//
//    @PostMapping("/api/organisations")
//    public void organisations() {
//    }


}

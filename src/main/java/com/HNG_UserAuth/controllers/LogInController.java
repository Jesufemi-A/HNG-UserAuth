package com.HNG_UserAuth.controllers;


import com.HNG_UserAuth.models.LoginUserDto;
import com.HNG_UserAuth.models.User;
import com.HNG_UserAuth.responseDto.RegisterUserResponseDto;
import com.HNG_UserAuth.responseDto.RegistrationErrorResponseDto;
import com.HNG_UserAuth.services.JwtService;
import com.HNG_UserAuth.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {

    private final UserService userService;
    private JwtService jwtProviderService;

    public LogInController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody LoginUserDto loginDetails) {


        try {

            User user = userService.authenticateUser(loginDetails.getEmail(), loginDetails.getPassword());

            if (user == null) {
                var errorResponseDto = new RegistrationErrorResponseDto("Bad request", "Registration unsuccessful", 400);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
            }

            String accesstoken = jwtProviderService.generateToken(user);
            var userDto = new RegisterUserResponseDto.Data.User();
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setUserId(user.getUserId());
            userDto.setPhone(user.getPhone());

            var data = new RegisterUserResponseDto.Data();
            data.setAccessToken(accesstoken);
            data.setUser(userDto);


            var registerUserDto = new RegisterUserResponseDto("success",
                    "Login sucessfull", data);
            return ResponseEntity.status(HttpStatus.CREATED).body(registerUserDto);
        } catch (Exception e) {
            var errorResponseDto = new RegistrationErrorResponseDto("Bad request",
                    "Authentication failed", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);

    }


}


}

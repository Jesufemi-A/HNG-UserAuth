package com.HNG_UserAuth.controllers;

import com.HNG_UserAuth.models.LoginUserDto;
import com.HNG_UserAuth.models.RegisterUserDto;
import com.HNG_UserAuth.models.User;
import com.HNG_UserAuth.responseDto.RegisterUserResponseDto;
import com.HNG_UserAuth.responseDto.RegistrationErrorResponseDto;
import com.HNG_UserAuth.services.AuthenticationService;
import com.HNG_UserAuth.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    Logger logger = Logger.getLogger("AuthenticationController.class");


    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto) {


        System.out.println("succsessseseses");


        try {

            System.out.println("Start");
            User registeredUser = authenticationService.signup(registerUserDto);

            String accesstoken = jwtService.generateToken(registeredUser);
            var userDto = new RegisterUserResponseDto.Data.User();
            userDto.setFirstName(registeredUser.getFirstName());
            userDto.setLastName(registeredUser.getLastName());
            userDto.setEmail(registeredUser.getEmail());
            userDto.setUserId(registeredUser.getUserId());
            userDto.setPhone(registeredUser.getPhone());

            var data = new RegisterUserResponseDto.Data();
            data.setAccessToken(accesstoken);
            data.setUser(userDto);

//            logger.info("token: "  + data.getAccessToken());

            var regUserDto = new RegisterUserResponseDto("success",
                    "Registration successful", data);


            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        } catch (Exception e) {
            var errorResponseDto = new RegistrationErrorResponseDto("Bad request", "Registration unsuccessful", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);

        }

    }

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody LoginUserDto loginUserDto) {
        try {
            User authenticatedUser = authenticationService.authenticate(loginUserDto);


            String accesstoken = jwtService.generateToken(authenticatedUser);
            var userDto = new RegisterUserResponseDto.Data.User();
            userDto.setFirstName(authenticatedUser.getFirstName());
            userDto.setLastName(authenticatedUser.getLastName());
            userDto.setEmail(authenticatedUser.getEmail());
            userDto.setUserId(authenticatedUser.getUserId());
            userDto.setPhone(authenticatedUser.getPhone());

            var data = new RegisterUserResponseDto.Data();
            data.setAccessToken(accesstoken);
            data.setUser(userDto);


            String jwtToken = jwtService.generateToken(authenticatedUser);

            var loginResponse = new RegisterUserResponseDto("success", "Login sucessful", data);

//                loginResponse.setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

            return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
        } catch (Exception e) {
            var errorResponseDto = new RegistrationErrorResponseDto("Bad request",
                    "Authentication failed", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);

        }
    }
}


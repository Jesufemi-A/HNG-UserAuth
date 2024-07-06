package com.HNG_UserAuth.services;

import jakarta.persistence.Column;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService  {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordEncoderService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public String encode(CharSequence password) {
        return bCryptPasswordEncoder.encode(password);
    }


    public boolean matches(CharSequence rawPassword, String password) {
        return bCryptPasswordEncoder.matches(rawPassword, password);
    }



}

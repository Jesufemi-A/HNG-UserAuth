package com.HNG_UserAuth.services;

import com.HNG_UserAuth.models.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

public class JwtProviderService {

    private String jwtSecret;

    private long jwtExpirationInMilliseconds;


    public String generateToken(UserModel user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("email", user.getEmail());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());



        return Jwts.builder()
                .setSubject(user.getEmail())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMilliseconds))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}

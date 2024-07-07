package com.HNG_UserAuth.controllers;


import com.HNG_UserAuth.models.Organisation;
import com.HNG_UserAuth.models.User;
import com.HNG_UserAuth.repository.UserRepository;
import com.HNG_UserAuth.responseDto.CreateOrganisaitonDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class LoggedInController {


    private UserRepository userRepository;

    public LoggedInController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/organisations")
    public Set<Organisation> getOrganisations(@AuthenticationPrincipal User user) {

        String email = user.getEmail();

        Optional<User> currentUser = userRepository.findByEmail(email);

        return user.getOrganisations();

    }


//    @GetMapping("/api/users/{id}")
//    public User getUserRecord(@PathVariable String  id, @AuthenticationPrincipal User user) {
//
//
//        Optional<User> currentUser = userRepository.findById(id);
//
//    }


//    @GetMapping("/api/organisations/{orgId}")
//    public getUserOrganisation(@PathVariable String orgId) {
//
//    }


    @PostMapping("/api/organisations/{orgId}/users")
    public void  addUserToOrganisation(@PathVariable String orgId) {

    }

    @PostMapping("/api/organisations")
    public void createOrganisation(CreateOrganisaitonDto createOrganisaitonDto,
                                   @AuthenticationPrincipal User user) {



    }



}

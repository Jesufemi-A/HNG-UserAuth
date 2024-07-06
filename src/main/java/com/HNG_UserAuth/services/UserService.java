package com.HNG_UserAuth.services;


import com.HNG_UserAuth.models.Organisation;
import com.HNG_UserAuth.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.HNG_UserAuth.models.UserModel;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoderService passwordEncoderService;

    public UserService(UserRepository userRepository, PasswordEncoderService passwordEncoderService) {
        this.userRepository = userRepository;
        this.passwordEncoderService = passwordEncoderService;
    }


    @Transactional
    public UserModel registerUser(UserModel user) {
        user.setPassword(passwordEncoderService.encode(user.getPassword()));
        user.getOrganisations().add(createOrganisation(user.getFirstName()));
        return userRepository.save(user);
    }


    public Organisation createOrganisation(String firstName) {
        Organisation organisation = new Organisation(firstName + "'s "
                + "Organisation");
        return organisation;
    }

    public UserModel authenticateUser(String email, String password) throws Exception {
        UserModel user = userRepository.findByEmail(email);
        if (user == null && !passwordEncoderService.matches(password, user.getPassword())) {
            throw new Exception("Invalid email or password");
        }
        return user;
    }
}

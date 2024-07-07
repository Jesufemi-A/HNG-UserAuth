package com.HNG_UserAuth.repository;

//import com.HNG_UserAuth.models.User;

import com.HNG_UserAuth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<String> findUserByUserId(String id);

    //   UserModel findByEmail(String email);
    Optional<User> findByEmail(String email);


}

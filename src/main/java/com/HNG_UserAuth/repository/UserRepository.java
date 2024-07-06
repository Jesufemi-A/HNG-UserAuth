package com.HNG_UserAuth.repository;

//import com.HNG_UserAuth.models.User;
import com.HNG_UserAuth.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

    Optional<String> findUserByUserId(String id);
}

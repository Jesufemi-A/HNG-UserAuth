package com.HNG_UserAuth.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import  java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class UserModel {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @NotNull
    @Column(name="user_id",  unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @NotNull
    @Size(min = 1, message = "First name must not be empty")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "LastName must not be empty")
    private String lastName;

    @NotNull
    @Email
    @Column(unique = true)
    @Email
    private String email;


    @NotNull
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;


    @NotNull
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_organisation",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "org_id", referencedColumnName = "org_id")
            }
    )
    private Set<Organisation> organisations = new HashSet<>() ;


//    @PrePersist
//    protected void onCreate(){
//        if (this.userId == null) {
//            this.userId= UUID.randomUUID().toString();
//        }
//    }

}

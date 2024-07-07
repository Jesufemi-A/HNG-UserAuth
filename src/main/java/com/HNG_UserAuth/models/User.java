package com.HNG_UserAuth.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

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
    @Column(unique = true, nullable = false,length = 50)
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;


}

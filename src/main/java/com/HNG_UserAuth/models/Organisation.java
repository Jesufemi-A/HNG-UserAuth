package com.HNG_UserAuth.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "organisation")
@Data
@NoArgsConstructor
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "org_id",  unique = true)
    private String orgId;

    @NotNull
    @Size(min=1, message = "name can not be empty")
    private String name;
    private String description;

    public Organisation(String name){
        this.name = name;
//        this.orgId = UUID.randomUUID().toString();
    }

//    @PrePersist
//    protected  void onCreate(){
//        if (orgId == null) {
//            this.orgId = UUID.randomUUID().toString();
//        }
//    }


    @ManyToMany(mappedBy="organisations", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<UserModel> userModels = new HashSet<>();

}

package com.admin.userservice.entity;

import com.admin.userservice.validation.ValidPassword;
import com.admin.userservice.enumuration.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String confirmPassword;

    private String fullName;

    private String country;

    private String state;

    private String district;

    private String aadhaar;

    private String pan_card;

    private String city;

    private String mobileNo;

    @Enumerated(EnumType.STRING)
    @Column(name="user_type")
    private UserType userType;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Column(name="role")
    private Set<Role> roles = new HashSet<>();


   /* @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;*/



  /*  @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="role",
            joinColumns = @JoinColumn(name="id")
    )
    @Column(name="role")
    private Set<String> roles;*/


}

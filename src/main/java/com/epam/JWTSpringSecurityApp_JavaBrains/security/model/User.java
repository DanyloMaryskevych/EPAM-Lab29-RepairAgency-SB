package com.epam.JWTSpringSecurityApp_JavaBrains.security.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Email should not be empty!")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Password should not be empty!")
    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @NotNull(message = "First Name should not be empty!")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last Name should not be empty!")
    @Column(name = "last_name")
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

//    @NotNull(message = " should not be empty!")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
}

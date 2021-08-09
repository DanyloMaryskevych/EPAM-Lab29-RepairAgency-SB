package com.epam.JWTSpringSecurityApp_JavaBrains.security.rest;

import com.epam.JWTSpringSecurityApp_JavaBrains.security.model.User;
import com.epam.JWTSpringSecurityApp_JavaBrains.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/register")
@AllArgsConstructor
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping
    public User newUser(@RequestBody User user) {
        userDetailsService.newUser(user);
        return user;
    }


}

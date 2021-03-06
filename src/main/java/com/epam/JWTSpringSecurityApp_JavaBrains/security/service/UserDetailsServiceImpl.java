package com.epam.JWTSpringSecurityApp_JavaBrains.security.service;

import com.epam.JWTSpringSecurityApp_JavaBrains.security.model.User;
import com.epam.JWTSpringSecurityApp_JavaBrains.security.model.UserDetailsConverter;
import com.epam.JWTSpringSecurityApp_JavaBrains.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return UserDetailsConverter.convertUser(user);
    }

    public User newUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}

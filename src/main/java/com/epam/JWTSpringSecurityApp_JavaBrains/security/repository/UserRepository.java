package com.epam.JWTSpringSecurityApp_JavaBrains.security.repository;

import com.epam.JWTSpringSecurityApp_JavaBrains.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    @Override
    <S extends User> S save(S s);
}

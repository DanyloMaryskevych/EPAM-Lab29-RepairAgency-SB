package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.repository;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Override
    Optional<Order> findById(Long id);

    @Override
    <S extends Order> S save(S s);

    @Override
    void deleteById(Long id);
}

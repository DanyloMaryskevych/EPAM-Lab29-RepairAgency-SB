package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.repository;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    @Override
    Optional<Order> findById(Long id);

    @Override
    <S extends Order> S save(S s);

    @Override
    void deleteById(Long id);

    boolean existsOrderById(Long id);

    @Override
    Page<Order> findAll(Pageable pageable);
}

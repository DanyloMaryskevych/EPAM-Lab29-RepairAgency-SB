package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.dao;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Order;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDAO {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Wrong order id!"));
    }

    public Order deleteOrderById(Long id) {
        Order order = findOrderById(id);
        orderRepository.deleteById(id);
        return order;
    }

}

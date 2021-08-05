package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.dao.OrderDAO;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Order;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.group.OnCreate;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.repository.OrderRepository;
import com.epam.JWTSpringSecurityApp_JavaBrains.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderDAO orderDAO;
    private final JwtUtil jwtUtil;
    private final HttpServletRequest request;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderDAO orderDAO, JwtUtil jwtUtil, HttpServletRequest request) {
        this.orderRepository = orderRepository;
        this.orderDAO = orderDAO;
        this.jwtUtil = jwtUtil;
        this.request = request;
    }

    @PostMapping
    public Order newOrder(@RequestBody @Validated(OnCreate.class) Order order) {
        Integer customerId = (Integer) jwtUtil.getId(jwtUtil.getToken(request));
        order.setCustomerId(Long.valueOf(customerId));
        orderDAO.saveOrder(order);
        return order;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        orderRepository.findById(id).ifPresent(order -> System.out.println(order.getTitle()));
        return orderRepository.findById(id).orElse(null);
    }
}

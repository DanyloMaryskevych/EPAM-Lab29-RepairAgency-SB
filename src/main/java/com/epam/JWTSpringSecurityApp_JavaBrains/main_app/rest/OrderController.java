package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.dao.OrderDAO;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Order;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.group.OnCreate;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.group.OnUpdate;
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

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        orderRepository.findById(id).ifPresent(order -> System.out.println(order.getTitle()));
        return orderRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Order newOrder(@RequestBody @Validated(OnCreate.class) Order order) {
        setCustomerIdFromJWT(order);
        orderDAO.saveOrder(order);
        return order;
    }

    @PutMapping("/{id}")
    public Order updateOrder(@RequestBody @Validated(OnUpdate.class) Order order, @PathVariable Long id) {
        order.setId(id);
        setCustomerIdFromJWT(order);
        orderRepository.save(order);
        return order;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }

    private void setCustomerIdFromJWT(Order order) {
        Integer customerId = (Integer) jwtUtil.getId(jwtUtil.getToken(request));
        order.setCustomerId(Long.valueOf(customerId));
    }

}

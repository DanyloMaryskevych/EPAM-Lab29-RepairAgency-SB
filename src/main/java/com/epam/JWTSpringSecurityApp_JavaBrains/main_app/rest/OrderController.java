package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.dao.OrderDAO;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Order;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.group.OnCreate;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.group.OnUpdate;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.repository.OrderRepository;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest.assembler.OrderAssembler;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest.model.OrderModel;
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
    private final OrderAssembler orderAssembler;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderDAO orderDAO, JwtUtil jwtUtil, HttpServletRequest request, OrderAssembler orderAssembler) {
        this.orderRepository = orderRepository;
        this.orderDAO = orderDAO;
        this.jwtUtil = jwtUtil;
        this.request = request;
        this.orderAssembler = orderAssembler;
    }

    @GetMapping("/{id}")
    public OrderModel getOrderById(@PathVariable Long id) {
        Order order = orderDAO.findOrderById(id);
        return orderAssembler.toModel(order);
    }

    @PostMapping
    public OrderModel newOrder(@RequestBody @Validated(OnCreate.class) Order order) {
        setCustomerIdFromJWT(order);
        orderDAO.saveOrder(order);
        return orderAssembler.toModel(order);
    }

    @PutMapping("/{id}")
    public OrderModel updateOrder(@RequestBody @Validated(OnUpdate.class) Order order, @PathVariable Long id) {
        order.setId(id);
        setCustomerIdFromJWT(order);
        orderDAO.saveOrder(order);
        return orderAssembler.toModel(order);
    }

    @DeleteMapping("/{id}")
    public OrderModel deleteOrder(@PathVariable Long id) {
        Order order = orderDAO.deleteOrderById(id);
        return orderAssembler.toModel(order);
    }

    private void setCustomerIdFromJWT(Order order) {
        Integer customerId = (Integer) jwtUtil.getId(jwtUtil.getToken(request));
        order.setCustomerId(Long.valueOf(customerId));
    }

}

package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.api.OrderApi;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.dao.OrderDAO;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Order;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest.assembler.OrderAssembler;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest.model.OrderModel;
import com.epam.JWTSpringSecurityApp_JavaBrains.security.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class OrderController implements OrderApi {

    private final OrderDAO orderDAO;
    private final JwtUtil jwtUtil;
    private final HttpServletRequest request;
    private final OrderAssembler orderAssembler;

    @Override
    public OrderModel getOrder(Long id) {
        Order order = orderDAO.findOrderById(id);
        return orderAssembler.toModel(order);
    }

    @Override
    public OrderModel newOrder(Order order) {
        setCustomerIdFromJWT(order);
        orderDAO.saveOrder(order);
        return orderAssembler.toModel(order);
    }

    @Override
    public OrderModel updateOrder(Order order, Long id) {
        order.setId(id);
        setCustomerIdFromJWT(order);
        orderDAO.updateOrder(order);
        return orderAssembler.toModel(order);
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Long id) {
        orderDAO.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    private void setCustomerIdFromJWT(Order order) {
        Integer customerId = (Integer) jwtUtil.getId(jwtUtil.getToken(request));
        order.setCustomerId(Long.valueOf(customerId));
    }

}

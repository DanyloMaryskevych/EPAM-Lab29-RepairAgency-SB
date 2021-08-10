package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.dao;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.exception.OrderNotFoundException;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Order;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OrderDAO {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findOrderById(Long id) {
        log.info("Looking for order with id '{}'...", id);
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        log.info("Order with id '{}' is found!", id);
        return order;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
        log.info("Order with id '{}' is successfully created!", order.getId());
    }

    public void updateOrder(Order order) {
        log.info("Updating order with id '{}'...", order.getId());
        if (orderRepository.existsOrderById(order.getId())) {
            orderRepository.save(order);
        }
        else throw new OrderNotFoundException();
        log.info("Order with id '{}' is successfully updated!", order.getId());
    }

    public void deleteOrderById(Long id) {
        log.info("Deleting order with id '{}'...", id);
        Order order = findOrderById(id);

        if (orderRepository.existsOrderById(id)) {
            orderRepository.deleteById(id);
        }
        else throw new OrderNotFoundException();
        log.info("Order with id '{}' is successfully deleted!", order.getId());

    }

    public List<Order> getOrders(String page) {
        int pageNum;
        try {
            pageNum = Integer.parseInt(page) - 1;
        } catch (NumberFormatException e) {
            log.info("Page parameter should be a valid number, not a '{}'!", page);
            throw new NumberFormatException();
        }
        Page<Order> orders = orderRepository.findAll(PageRequest.of(pageNum, 5));
        System.out.println(orders);
        if (orders.getTotalPages() <= pageNum) throw new IllegalArgumentException("Wrong page number!");

        return orders.getContent();
    }

}

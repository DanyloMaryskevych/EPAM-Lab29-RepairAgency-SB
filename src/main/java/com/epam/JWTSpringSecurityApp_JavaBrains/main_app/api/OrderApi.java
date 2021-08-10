package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.api;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Order;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.group.OnCreate;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.group.OnUpdate;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest.model.OrderModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RequestMapping("/api/v1/order")
public interface OrderApi {

    @ApiOperation("Get all orders")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<Order> getAllOrders(@RequestParam(name = "page") String page);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", readOnly = true, value = "Order id")
    })
    @ApiOperation("Get order")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    OrderModel getOrder(@PathVariable Long id);

    @ApiOperation("Create order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    OrderModel newOrder(@RequestBody @Validated(OnCreate.class) Order order);

    @ApiOperation("Update order")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    OrderModel updateOrder(@RequestBody @Validated(OnUpdate.class) Order order, @PathVariable Long id);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order id"),
    })
    @ApiOperation("Delete order")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable Long id);
}

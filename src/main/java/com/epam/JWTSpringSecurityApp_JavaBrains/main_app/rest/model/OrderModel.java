package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest.model;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Order;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class OrderModel extends RepresentationModel<OrderModel> {

    @JsonUnwrapped
    private Order order;
}

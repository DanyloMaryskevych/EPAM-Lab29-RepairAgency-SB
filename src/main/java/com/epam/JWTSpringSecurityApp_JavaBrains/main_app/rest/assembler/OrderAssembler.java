package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest.assembler;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Order;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest.OrderController;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest.model.OrderModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderAssembler extends RepresentationModelAssemblerSupport<Order, OrderModel> {

    public static final String GET_REL = "get_order";
    public static final String CREATE_REL = "create_order";
    public static final String UPDATE_REL = "update_order";
    public static final String DELETE_REL = "delete_order";

    public OrderAssembler() {
        super(OrderController.class, OrderModel.class);
    }

    @Override
    public OrderModel toModel(Order entity) {
        OrderModel orderModel = new OrderModel(entity);

        Link get = linkTo(methodOn(OrderController.class).getOrderById(entity.getId())).withRel(GET_REL);
        Link create = linkTo(methodOn(OrderController.class).newOrder(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(OrderController.class).updateOrder(entity, entity.getId()))
                .withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(OrderController.class).deleteOrder(entity.getId()))
                .withRel(DELETE_REL);

        orderModel.add(get, create, update, delete);
        return orderModel;
    }

}

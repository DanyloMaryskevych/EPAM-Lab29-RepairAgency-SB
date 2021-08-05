package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.group.OnCreate;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.group.OnUpdate;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "bh_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @NotBlank(message = "Title shouldn't be empty!", groups = OnCreate.class)
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Description shouldn't be empty!", groups = OnUpdate.class)
    @Column(name = "description")
    private String description;
}

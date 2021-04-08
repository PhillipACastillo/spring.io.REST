package com.springframework.payroll;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PathVariable;

public class OrderController {
    private final OrderRepository repository;
    private final OrderModelAssembler assembler;

    OrderController(OrderRepository orderRepository,
        OrderModelAssembler orderModelAssembler) {
        this.repository = orderRepository;
        this.assembler = orderModelAssembler;
    }

    EntityModel<Order> one (@PathVariable Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return assembler.toModel(order);
    }
}

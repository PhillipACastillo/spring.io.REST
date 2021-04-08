package com.springframework.payroll;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class OrderController {
    private final OrderRepository repository;
    private final OrderModelAssembler assembler;

    OrderController(OrderRepository orderRepository,
        OrderModelAssembler orderModelAssembler) {
        this.repository = orderRepository;
        this.assembler = orderModelAssembler;
    }

    @GetMapping("/orders/{id}")
    EntityModel<Order> one (@PathVariable Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return assembler.toModel(order);
    }
}

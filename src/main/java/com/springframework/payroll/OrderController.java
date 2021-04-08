package com.springframework.payroll;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class OrderController {
    private final OrderRepository repository;
    private final OrderModelAssembler assembler;

    OrderController(OrderRepository orderRepository,
        OrderModelAssembler orderModelAssembler) {
        this.repository = orderRepository;
        this.assembler = orderModelAssembler;
    }

    @GetMapping("/orders")
    CollectionModel<EntityModel<Order>> all() {
        List<EntityModel<Order>> orders = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders,
                linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/orders/{id}")
    EntityModel<Order> one (@PathVariable Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return assembler.toModel(order);
    }
}

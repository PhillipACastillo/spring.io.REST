package com.springframework.payroll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.aspectj.weaver.ast.Or;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler
        implements RepresentationModelAssembler<Order, EntityModel<Order>> {
    @Override
    public EntityModel<Order> toModel(Order entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(OrderController.class)
                        .one(entity.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all())
                        .withRel("orders"));
    }
}

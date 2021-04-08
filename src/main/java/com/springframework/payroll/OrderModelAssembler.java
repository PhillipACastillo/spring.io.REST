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
        EntityModel<Order> orderModel = EntityModel.of(entity,
                linkTo(methodOn(OrderController.class)
                        .one(entity.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all())
                        .withRel("orders"));

        if (entity.getStatus() == Status.IN_PROGRESS) {
            orderModel.add(linkTo(methodOn(OrderController.class)
                    .cancel(entity.getId())).withRel("cancel"));
        }

        return orderModel;
    }
}

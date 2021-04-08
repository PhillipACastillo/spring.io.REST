package com.springframework.payroll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeModelAssembler
    implements RepresentationModelAssembler<Employee, EntityModel<Employee>>{

    @Override
    public EntityModel<Employee> toModel(Employee employee) {
        return EntityModel.of(employee,
            linkTo(methodOn(EmployeeController.class).one(employee.getId()))
                .withSelfRel(),
            linkTo(methodOn(EmployeeController.class).all())
                .withRel("employees"));
    }
}

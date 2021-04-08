package com.springframework.payroll;

public class OrderNotFoundException extends RuntimeException{
    OrderNotFoundException (Long id) {
        super("Could not find Order with an id of : " + id);
    }
}

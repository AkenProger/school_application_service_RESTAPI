package com.example.demo.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderExceptions extends RuntimeException {
    public OrderExceptions(String message) {
        super(message);
    }
}

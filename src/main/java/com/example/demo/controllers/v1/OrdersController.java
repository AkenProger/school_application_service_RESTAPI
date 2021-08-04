package com.example.demo.controllers.v1;

import com.example.demo.controllers.BaseController;
import com.example.demo.models.dto.OrdersDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrdersController implements BaseController<OrdersDto, Long> {
    @Override
    public OrdersDto save(OrdersDto ordersDto) {
        return null;
    }

    @Override
    public OrdersDto update(OrdersDto ordersDto) {
        return null;
    }

    @Override
    public OrdersDto findById(Long id) {
        return null;
    }

    @Override
    public List<OrdersDto> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

package com.example.demo.controllers.v1;

import com.example.demo.controllers.BaseController;
import com.example.demo.models.Response;
import com.example.demo.models.dto.OrdersDto;
import com.example.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrdersController implements BaseController<OrdersDto, Long> {

    @Autowired
    OrdersService ordersService;

    @PostMapping("/saveOrder")
    public Response saveOrder(@RequestBody OrdersDto ordersDto) {
        return ordersService.saveOrder(ordersDto);
    }

    @Override
    public OrdersDto save(OrdersDto ordersDto) {
        return ordersService.save(ordersDto);
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
        return ordersService.findAll();
    }

    @Override
    public void deleteById(Long id) {

    }
}

package com.example.demo.service;

import com.example.demo.models.Response;
import com.example.demo.models.dto.OrdersDto;

public interface OrdersService extends BaseCrudService<OrdersDto, Long>{
  Response saveOrder(OrdersDto ordersDto);
}

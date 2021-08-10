package com.example.demo.service;

import com.example.demo.models.Response;
import com.example.demo.models.dto.OrdersDto;

import java.util.List;

public interface OrdersService extends BaseCrudService<OrdersDto, Long>{
  Response saveOrder(OrdersDto ordersDto);

  List<OrdersDto> getAllNewOrders();

  Response processingOrder(Long id);

  Response acceptOrder(Long id);

  Response deniedOrderById(Long id, String comment);
}

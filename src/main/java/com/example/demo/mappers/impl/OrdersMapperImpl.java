package com.example.demo.mappers.impl;

import com.example.demo.mappers.OrdersMapper;
import com.example.demo.models.dto.OrdersDto;
import com.example.demo.models.entities.Orders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrdersMapperImpl implements OrdersMapper {
    @Override
    public OrdersDto toDto(Orders orders) {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setId(orders.getId());
        ordersDto.setSchoolName(orders.getSchoolName());
        ordersDto.setAdd_date(orders.getAdd_date());
        ordersDto.setEnd_date(orders.getEnd_date());
        ordersDto.setAddress(orders.getAddress());
        ordersDto.setComment(orders.getComment());
        ordersDto.setOrderStatuses(orders.getOrderStatuses());
        ordersDto.setSubscriber(orders.getSubscriber());
        ordersDto.setNavi_date(orders.getNavi_date());
        return ordersDto;
    }

    @Override
    public Orders toEntity(OrdersDto ordersDto) {
        Orders orders = new Orders();
        orders.setId(ordersDto.getId());
        orders.setSchoolName(ordersDto.getSchoolName());
        orders.setAdd_date(ordersDto.getAdd_date());
        orders.setEnd_date(ordersDto.getEnd_date());
        orders.setAddress(ordersDto.getAddress());
        orders.setComment(ordersDto.getComment());
        orders.setOrderStatuses(ordersDto.getOrderStatuses());
        orders.setSubscriber(ordersDto.getSubscriber());
        orders.setNavi_date(ordersDto.getNavi_date());
        return orders;
    }

    @Override
    public List<OrdersDto> toDtoList(List<Orders> entities) {
        return entities.stream().map(s -> toDto(s)).collect(Collectors.toList());
    }

    @Override
    public List<Orders> toEntities(List<OrdersDto> dtos) {
        return dtos.stream().map(s -> toEntity(s)).collect(Collectors.toList());
    }
}

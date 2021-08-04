package com.example.demo.service.impl;

import com.example.demo.dao.OrderRepository;
import com.example.demo.exeptions.OrderExceptions;
import com.example.demo.mappers.OrdersMapper;
import com.example.demo.models.dto.OrdersDto;
import com.example.demo.models.entities.Orders;
import com.example.demo.models.enums.OrderStatuses;
import com.example.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrdersDto save(OrdersDto ordersDto) {
        return null;
    }

    @Override
    public OrdersDto update(OrdersDto ordersDto) {
        return null;
    }

    @Override
    public List<OrdersDto> findAll() {
        return ordersMapper.toDtoList(orderRepository.findAll());
    }

    @Override
    public OrdersDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }


    public int checkingTheStatusOfTheLastClient(OrdersDto ordersDto) {
        Orders orders = orderRepository.findByEndDateAndSubscriberSubsId(null, ordersDto.getSubscriber().getId());
        if (orders != null) {
            if (orders.getOrderStatuses().equals(OrderStatuses.NEW) ||
                    orders.getOrderStatuses().equals(OrderStatuses.DENIED) || orders.getOrderStatuses().equals(OrderStatuses.APPROVED)) {
                orders.setOrderStatuses(OrderStatuses.CANCELED);
                orders.setEnd_date(new Date());
                orderRepository.save(orders);
                return 1;
            }

            if (orders.getOrderStatuses().equals(OrderStatuses.PROCESSED)) {
                return 2;
            }
        } else {
            return 0;
        }
        throw new OrderExceptions("Ошибка при изменении!");

    }
}

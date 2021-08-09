package com.example.demo.service.impl;

import com.example.demo.dao.OrderRepository;
import com.example.demo.exeptions.OrderExceptions;
import com.example.demo.mappers.OrdersMapper;
import com.example.demo.mappers.SubscriberMapper;
import com.example.demo.models.Response;
import com.example.demo.models.dto.OrdersDto;
import com.example.demo.models.dto.SubscriberDto;
import com.example.demo.models.entities.Orders;
import com.example.demo.models.entities.Subscriber;
import com.example.demo.models.enums.OrderStatuses;
import com.example.demo.service.OrdersService;
import com.example.demo.service.SubscriberService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.enums.OrderStatuses;

import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {


    private Response response = new Response();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private SubscriberService subscriberService;



    @Override
    public Response saveOrder(OrdersDto ordersDto) {

        int statusCode = checkingTheStatusOfTheLastClient(ordersDto);
        switch (statusCode) {
            case 1:
            case 0:
                saveNewOrders(ordersDto);
                System.out.println(response.getMessage());
                response.setCode(1);
                response.setMessage("Ваша заявка принята!");
                return response;
            case 2:
                System.out.println(response.getMessage());
                response.setCode(0);
                response.setMessage("Ответ в рассмотрении!");
                return response;
        }
        response.setCode(0);
        response.setMessage("Повторите попытку!");
        return response;
    }


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


    //Проверка статус заявки
    public int checkingTheStatusOfTheLastClient(OrdersDto ordersDto) {
        Orders orders = orderRepository.findByEndDateAndSubscriberSubsId(ordersDto.getSubscriber().getId());
        if (orders != null) {
            if (orders.getOrderStatuses().equals(OrderStatuses.NEW)
                    || orders.getOrderStatuses().equals(OrderStatuses.DENIED)
                    || orders.getOrderStatuses().equals(OrderStatuses.APPROVED)) {

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
    //Получить все новые заявки
    public List<OrdersDto> getAllNewOrders() {
        return ordersMapper.toDtoList(orderRepository.findByOrderStatusAndEndDate(OrderStatuses.NEW));
    }
    //Сохранение заявки
    public void saveNewOrders(OrdersDto ordersDto) {
        ordersDto.setAdd_date(new Date());
        ordersDto.setOrderStatuses(OrderStatuses.NEW);
        Orders orders = ordersMapper.toEntity(ordersDto);
        orderRepository.save(orders);
    }


}

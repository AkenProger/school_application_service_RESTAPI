package com.example.demo.service.impl;

import com.example.demo.dao.OrderRepository;
import com.example.demo.exeptions.OrderExceptions;
import com.example.demo.mappers.OrdersMapper;
import com.example.demo.models.Response;
import com.example.demo.models.dto.OrdersDto;
import com.example.demo.models.entities.Orders;
import com.example.demo.models.enums.OrderStatuses;
import com.example.demo.service.OrdersService;
import com.example.demo.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                save(ordersDto);
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
        ordersDto.setAdd_date(new Date());
        ordersDto.setOrderStatuses(OrderStatuses.NEW);
        Orders orders = ordersMapper.toEntity(ordersDto);
        orderRepository.save(orders);
        return ordersMapper.toDto(orders);
    }

    @Override
    public OrdersDto update(OrdersDto ordersDto) {
        if (orderRepository.existsById(ordersDto.getId()))
            new Response().setMessage("Не найден заявка с такой ID номером!");
        Orders orders = ordersMapper.toEntity(ordersDto);
        orders = orderRepository.save(orders);
        return ordersMapper.toDto(orders);
    }

    @Override
    public List<OrdersDto> findAll() {
        return ordersMapper.toDtoList(orderRepository.findAll());
    }

    @Override
    public OrdersDto findById(Long id) {
        Orders orders = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найден заявка с такой ID!"));
        return ordersMapper.toDto(orders);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
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
        return ordersMapper.toDtoList(orderRepository.findByOrderStatusAndEndDate());
    }

    @Override
    public Response processingOrder(Long id) {
        Response response = new Response();
        if (orderRepository.existsById(id)) {

            Orders orders = orderRepository.findById(id).get();
            if (orders.getOrderStatuses().equals(OrderStatuses.NEW) && orders.getEnd_date() == null) {
                orders.setEnd_date(new Date());
                orders.setNavi_date(new Date());
                orders.setOrderStatuses(OrderStatuses.PROCESSED);
                orderRepository.save(orders);
                response.setCode(1);
                response.setMessage("Ваша заявка принята на рассмотрение!");
                return response;
            } else {
                response.setCode(0);
                response.setMessage("Невозможно принять заявку на рассмотрение!");
                return response;
            }

        } else {
            response.setCode(0);
            response.setMessage("Результаты по данному ID не найдены.Введите корректные данные!");
        }
        return null;
    }

    @Override
    public Response acceptOrder(Long id) {
        Response response = new Response();
        Orders orders = orderRepository.findByStatusWhereProcess(id);
        if (orders != null) {
            orders.setEnd_date(new Date());
            orders.setOrderStatuses(OrderStatuses.APPROVED);
            orderRepository.save(orders);
            response.setCode(1);
            response.setMessage("Ваша заявка принята!");
            return response;
        }
        response.setCode(0);
        response.setMessage("Повторите попытку ещё раз!");
        return response;
    }

    @Override
    public Response deniedOrderById(Long id, String comment) {
        Response response = new Response();
        Orders orders = orderRepository.findByStatusWhereProcess(id);
        if (orders != null) {
            orders.setEnd_date(new Date());
            orders.setComment(comment);
            orders.setOrderStatuses(OrderStatuses.DENIED);
            orderRepository.save(orders);
            response.setCode(0);
            response.setMessage("Ваша заявка отклонена!");
            return response;
        }
        response.setCode(0);
        response.setMessage("Повторите попытку ещё раз!");
        return response;
    }


}

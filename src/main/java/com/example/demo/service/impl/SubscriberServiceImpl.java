package com.example.demo.service.impl;

import com.example.demo.dao.SubscriberRepository;
import com.example.demo.mappers.SubscriberMapper;
import com.example.demo.models.dto.SubscriberDto;
import com.example.demo.models.entities.Subscriber;
import com.example.demo.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    SubscriberMapper subscriberMapper;

    @Autowired
    SubscriberRepository subscriberRepository;



    @Override
    public SubscriberDto save(SubscriberDto subscriberDto) {
        Subscriber subscriber = subscriberRepository.findByPhoneNumber(subscriberDto.getPhoneNumber());
        if (subscriber != null) {
            throw new RuntimeException("Такой user есть!");
        }
        subscriber = subscriberMapper.toEntity(subscriberDto);
        subscriber = subscriberRepository.save(subscriber);
        return subscriberMapper.toDto(subscriber);
    }


    @Override
    public SubscriberDto update(SubscriberDto subscriberDto) {
        return null;
    }

    @Override
    public List<SubscriberDto> findAll() {
        return null;
    }

    @Override
    public SubscriberDto findById(Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}

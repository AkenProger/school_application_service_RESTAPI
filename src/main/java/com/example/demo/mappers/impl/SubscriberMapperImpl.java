package com.example.demo.mappers.impl;

import com.example.demo.mappers.SubscriberMapper;
import com.example.demo.models.dto.SubscriberDto;
import com.example.demo.models.entities.Subscriber;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class SubscriberMapperImpl implements SubscriberMapper {
    @Override
    public SubscriberDto toDto(Subscriber subscriber) {
        SubscriberDto subscriberDto = new SubscriberDto();
        subscriberDto.setId(subscriber.getId());
        subscriberDto.setName(subscriber.getName());
        subscriberDto.setDate_birth(subscriber.getDate_birth());
        subscriberDto.setPhoneNumber(subscriber.getPhoneNumber());
        subscriberDto.setAge(subscriber.getAge());
        return subscriberDto;
    }

    @Override
    public Subscriber toEntity(SubscriberDto subscriberDto) {
        Subscriber subscriber = new Subscriber();
        subscriber.setId(subscriberDto.getId());
        subscriber.setName(subscriberDto.getName());
        subscriber.setDate_birth(subscriberDto.getDate_birth());
        subscriber.setPhoneNumber(subscriberDto.getPhoneNumber());
        subscriber.setAge(subscriberDto.getAge());
        return subscriber;
    }

    @Override
    public List<SubscriberDto> toDtoList(List<Subscriber> entities) {
        return entities.stream().map(s-> toDto(s)).collect(Collectors.toList());
    }

    @Override
    public List<Subscriber> toEntities(List<SubscriberDto> dtos) {
        return dtos.stream().map(s-> toEntity(s)).collect(Collectors.toList());
    }
}

package com.example.demo.controllers.v1;

import com.example.demo.controllers.BaseController;
import com.example.demo.models.dto.SubscriberDto;
import com.example.demo.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/subscriber")
public class SubscriberController implements BaseController<SubscriberDto, Long> {

    @Autowired
    SubscriberService subscriberService;
    @Override
    public SubscriberDto save(SubscriberDto subscriberDto) {
        return subscriberService.save(subscriberDto);
    }

    @Override
    public SubscriberDto update(SubscriberDto subscriberDto) {
        return null;
    }

    @Override
    public SubscriberDto findById(Long id) {
        return null;
    }

    @Override
    public List<SubscriberDto> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

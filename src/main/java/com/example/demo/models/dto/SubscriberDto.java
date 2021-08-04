package com.example.demo.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SubscriberDto {
    private Long id;
    private String name;
    private Date date_birth;
    private String phoneNumber;
    private int age;
}

package com.example.demo.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SubscriberOrderDto {
    private String name;
    private String phoneNumber;
    private String school_address;
    private String school_name;
    private Date date_birth;
}

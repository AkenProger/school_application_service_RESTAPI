package com.example.demo.models.dto;

import com.example.demo.models.entities.Subscriber;
import com.example.demo.models.enums.OrderStatuses;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class OrdersDto {
    private Long id;
    private String schoolName;
    private String address;
    private Date add_date;
    private Date end_date;
    private Date navi_date;
    private String comment;
    private Subscriber subscriber;
    private OrderStatuses orderStatuses;
}

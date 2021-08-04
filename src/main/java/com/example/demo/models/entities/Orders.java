package com.example.demo.models.entities;

import com.example.demo.models.enums.OrderStatuses;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String schoolName;
    private String address;
    @CreationTimestamp
    private Date add_date;
    private Date end_date;
    private Date navi_date;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "id_subscriber")
    private Subscriber subscriber;
    @Enumerated(EnumType.STRING)
    private OrderStatuses orderStatuses;


}

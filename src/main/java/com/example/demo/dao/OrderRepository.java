package com.example.demo.dao;

import com.example.demo.models.entities.Orders;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    Orders findByEndDateAndSubscriberSubsId(Date end_date, Long id);

}

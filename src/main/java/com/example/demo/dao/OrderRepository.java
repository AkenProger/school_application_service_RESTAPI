package com.example.demo.dao;

import com.example.demo.models.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
     @Query(value = "select * from subscriber s, orders o where o.end_date = '?1' and s.id = ?2", nativeQuery = true)
      Orders findByEndDateAndSubscriberSubsId(Date end_date, Long id);

}

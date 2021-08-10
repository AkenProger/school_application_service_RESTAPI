package com.example.demo.dao;

import com.example.demo.models.entities.Orders;
import com.example.demo.models.enums.OrderStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query(value = "select * from subscriber s, orders o where o.end_date = null and s.id = ?1", nativeQuery = true)
    Orders findByEndDateAndSubscriberSubsId(Long id);

    @Query(value = "select * from orders o where o.order_statuses = 'NEW' and o.end_date is NULL", nativeQuery = true)
    List<Orders> findByOrderStatusAndEndDate();

    @Query(value = "select * from orders o where o.order_statuses = 'PROCESSED' and o.id =?1",nativeQuery = true)
    Orders findByStatusWhereProcess(Long id);

    //Orders findByEndDateAndOrderStatusAndSubscriberSubsId(Date date, OrderStatuses orderStatus, Long id);

}

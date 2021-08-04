package com.example.demo.dao;

import com.example.demo.models.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    @Query(value = "select * from subscriber s where s.phone_number = ?1", nativeQuery = true)
    Subscriber findByPhoneNumber(String phoneNumber);
}

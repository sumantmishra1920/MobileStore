package com.mobilestore.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobilestore.main.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}

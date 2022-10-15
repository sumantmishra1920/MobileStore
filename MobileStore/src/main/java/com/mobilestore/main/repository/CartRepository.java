package com.mobilestore.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobilestore.main.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer> {

}

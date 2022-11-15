package com.mycompany.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.oms.entities.Cart;

//Repository for cart
@Repository
public interface ICartRepository extends JpaRepository<Cart, Integer>{

}

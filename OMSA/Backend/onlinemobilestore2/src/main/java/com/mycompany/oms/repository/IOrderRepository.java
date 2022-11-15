package com.mycompany.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.oms.entities.Order;

//Repository for mobile
@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer>{
	
	//custom query for a method to find list of orders from given customer
	@Query("select o from Order o where o.customer.customerId=:id")
	List<Order> findByCustomer(@Param("id") int id);
}

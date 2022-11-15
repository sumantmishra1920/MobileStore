package com.mycompany.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.oms.entities.Customer;

//Repository for customers
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

}

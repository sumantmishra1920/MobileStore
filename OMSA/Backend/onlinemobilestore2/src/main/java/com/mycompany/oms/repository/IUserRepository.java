package com.mycompany.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.oms.entities.User;

//Repository for users
@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
	
	//method to find user by given username
	User findByUserName(String userName);
}

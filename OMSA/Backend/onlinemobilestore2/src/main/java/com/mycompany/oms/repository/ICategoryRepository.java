package com.mycompany.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.oms.entities.Category;

//Repository for category
@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer>{

}

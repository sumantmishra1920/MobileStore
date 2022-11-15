package com.mycompany.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.oms.entities.Category;
import com.mycompany.oms.entities.Mobile;

//Repository for mobile
@Repository
public interface IMobileRepository extends JpaRepository<Mobile, Integer>{

	//method to count mobiles with same name
	long countByMobileName(String mobileName);
	
	//custom query for a method to find list of mobiles with given name
	@Query("select m from Mobile m where m.mobileName=:name")
	List<Mobile> findByMobileName(@Param("name") String mobileName);
	
	//custom query for a method to find list of mobiles in a given price range
	@Query("select m from Mobile m where m.mobileCost>=:price1 and m.mobileCost<=:price2")
	List<Mobile> findMobilesInPriceRange(@Param("price1") float price1, @Param("price2") float price2);
	
	//custom query for a method to find list of mobiles which belong to given category
	@Query("select m from Mobile m where m.category=:category")
	List<Mobile> findMobilesInCategory(@Param("category") Category category);
	
	//custom query for a method to find list of mobiles from given company
	@Query("select m from Mobile m where m.companyName=:company")
	List<Mobile> findAllMobilesFromCompany(@Param("company") String companyName);
}

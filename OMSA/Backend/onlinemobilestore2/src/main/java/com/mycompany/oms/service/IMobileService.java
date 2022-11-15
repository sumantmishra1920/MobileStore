package com.mycompany.oms.service;

import java.util.List;

import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.exceptions.MobileNotFoundException;
import com.mycompany.oms.exceptions.NoSuchCategoryException;

//Mobile service
public interface IMobileService {
	
	
	public Mobile addMobile(Mobile mobile);
	
	public Mobile updateMobile(int id, Mobile mobile) throws MobileNotFoundException;
	
	public Mobile deleteMobile(int id) throws MobileNotFoundException;
	
	public List<Mobile> showAllMobiles();
	
	public List<Mobile> showMobilesByName(String mobileName);
	
	public Mobile showMobileById(int id) throws MobileNotFoundException;
	
	public long availableQuantity(String mobileName);
	
	public List<Mobile> showMobilesByPriceRange(float price1, float price2);
	
	public List<Mobile> showMobilesByCategory(int categoryId) throws NoSuchCategoryException;
	
	public List<Mobile> showMobilesByCompany(String companyName);

}
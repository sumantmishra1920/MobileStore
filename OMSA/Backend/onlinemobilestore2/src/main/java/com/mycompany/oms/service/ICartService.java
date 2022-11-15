package com.mycompany.oms.service;

import java.util.List;

import com.mycompany.oms.entities.Cart;
import com.mycompany.oms.entities.Customer;
import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.exceptions.CartNotFoundException;
import com.mycompany.oms.exceptions.MobileNotFoundException;

//Cart service
public interface ICartService {
	
	Cart createCart(Customer customer);
	
	List<Cart> showAll();
	
	Cart deleteMobile(int cartId, int mobileId) throws CartNotFoundException, MobileNotFoundException;
	
	Cart deleteMobiles(int cartId) throws CartNotFoundException;
	
	Cart addToCart(int cartId,int mobileId) throws CartNotFoundException, MobileNotFoundException;
	
	List<Mobile> viewMobiles(int cartId) throws CartNotFoundException;
	
	Cart viewCartInfo(int cartId) throws CartNotFoundException;
	
	public Cart addMobiles(int cartId,String mobileName, int qty) throws MobileNotFoundException, CartNotFoundException;

}

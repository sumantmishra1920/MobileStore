package com.mycompany.oms.service;

import java.time.LocalDate;
import java.util.List;

import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.entities.Order;
import com.mycompany.oms.exceptions.CartNotFoundException;
import com.mycompany.oms.exceptions.CustomerNotFoundException;
import com.mycompany.oms.exceptions.MobileNotFoundException;
import com.mycompany.oms.exceptions.OrderNotFoundException;

//Order service
public interface IOrderService {
	
	public Order addOrder(int cartId, int mobileId) throws CartNotFoundException, MobileNotFoundException;
	
	public Order updateOrder(int id, Order order) throws OrderNotFoundException;
	
	public Order updateOrderStatus(int id, String status) throws OrderNotFoundException;
	
	public Order updateDispatchDate(int id, LocalDate date) throws OrderNotFoundException;
	
	public Order cancelOrder(int id) throws OrderNotFoundException;
	
	public Mobile showMobile(int id) throws OrderNotFoundException;
	
	public int calculateTotalCost(int cost);
	
	public Order showOrder(int id) throws OrderNotFoundException;
	
	public List<Order> showAllOrders();
	
	public List<Order> showOrderByCustomer(int id) throws CustomerNotFoundException;

}

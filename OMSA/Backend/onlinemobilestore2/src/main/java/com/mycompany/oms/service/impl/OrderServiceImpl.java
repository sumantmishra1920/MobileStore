package com.mycompany.oms.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.oms.entities.Cart;
import com.mycompany.oms.entities.Customer;
import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.entities.Order;
import com.mycompany.oms.exceptions.CartNotFoundException;
import com.mycompany.oms.exceptions.CustomerNotFoundException;
import com.mycompany.oms.exceptions.MobileNotFoundException;
import com.mycompany.oms.exceptions.OrderNotFoundException;
import com.mycompany.oms.repository.ICartRepository;
import com.mycompany.oms.repository.ICustomerRepository;
import com.mycompany.oms.repository.IMobileRepository;
import com.mycompany.oms.repository.IOrderRepository;
import com.mycompany.oms.service.ICartService;
import com.mycompany.oms.service.IOrderService;

//Order service implementation
@Service
public class OrderServiceImpl implements IOrderService{

	@Autowired
	private IOrderRepository orderRepository;
	
	@Autowired
	private ICartRepository cartRepository;
	
	@Autowired
	private ICustomerRepository customerRepository;
	
	@Autowired
	private IMobileRepository mobileRepository;
	
	@Autowired
	private ICartService cartService;
	
	public OrderServiceImpl(IOrderRepository orderRepository, ICartRepository cartRepository,
			ICustomerRepository customerRepository, IMobileRepository mobileRepository, ICartService cartService) {
		super();
		this.orderRepository = orderRepository;
		this.cartRepository = cartRepository;
		this.customerRepository = customerRepository;
		this.mobileRepository = mobileRepository;
		this.cartService = cartService;
	}

	//To place an order for a mobile present in the cart with given cartId
	@Override
	public Order addOrder(int cartId,int mobileId) throws CartNotFoundException, MobileNotFoundException {
		Optional<Cart> c = cartRepository.findById(cartId);
		if(c.isPresent()) {
			Cart cart=c.get();
			Optional<Mobile> opt = mobileRepository.findById(mobileId);
			if(opt.isPresent()) {
				Mobile mobile = opt.get();
				if(cart.getMobiles().contains(mobile)) {
					int cost = (int)mobile.getMobileCost();
					int totalCost = calculateTotalCost(cost);
					Customer cust = cart.getCustomer();
					Order order = new Order();
					order.setOrderDate(LocalDate.now());
					order.setDispatchDate(LocalDate.now().plusDays(1));
					order.setCost(cost);
					order.setTotalCost(totalCost);
					order.setStatus("Ordered");
					order.setCustomer(cust);
					order.setMobile(mobile);
					cartService.deleteMobile(cartId, mobileId);
					cartRepository.save(cart);
					orderRepository.save(order);
					return order;
				} else {
					throw new MobileNotFoundException("No mobile found with given mobileId in the cart");
				}
			} else {
				throw new MobileNotFoundException("No mobile found with given mobileId");
			}
			
		} else {
			String s="Cart not found with id "+cartId;
		    throw new CartNotFoundException(s);
	    }
	}

	//To update an order
	@Override
	public Order updateOrder(int id,Order order) throws OrderNotFoundException {
		Optional<Order> opt = orderRepository.findById(id);
		if(opt.isPresent()) {
			Order existingOrder = opt.get();
			existingOrder.setOrderDate(order.getOrderDate());
			existingOrder.setDispatchDate(order.getDispatchDate());
			existingOrder.setCost(order.getCost());
			existingOrder.setTotalCost(order.getTotalCost());
			existingOrder.setCustomer(order.getCustomer());
			existingOrder.setMobile(order.getMobile());
			orderRepository.save(existingOrder);
			return existingOrder;
		} else {
			throw new OrderNotFoundException("No order found with given order id");
		}
	}
	
	//To update status of an order
	@Override
	public Order updateOrderStatus(int id, String status) throws OrderNotFoundException{
		Optional<Order> opt=orderRepository.findById(id);
		if(opt.isPresent()) {
			Order order = opt.get();
			order.setStatus(status);
			orderRepository.save(order);
			return order;
		} else {
			throw new OrderNotFoundException("No order found with given order id");
		}
	}

	//To update dispatch date of an order
	@Override
	public Order updateDispatchDate(int id, LocalDate date) throws OrderNotFoundException{
		Optional<Order> opt = orderRepository.findById(id);
		if(opt.isPresent()) {
			Order order = opt.get();
			order.setDispatchDate(date);
			orderRepository.save(order);
			return order;
		} else {
			throw new OrderNotFoundException("No order found with given order id");
		}
	}

	//To cancel an order
	@Override
	public Order cancelOrder(int id) throws OrderNotFoundException {
		Optional<Order> opt = orderRepository.findById(id);
		if(opt.isPresent()) {
			Order order = opt.get();
			order.setStatus("Cancelled");
			orderRepository.save(order);
			mobileRepository.save(order.getMobile());
			return order;
		} else {
			throw new OrderNotFoundException("No order found with given order id");
		}
	}

	//To show information of mobile ordered
	@Override
	public Mobile showMobile(int id) throws OrderNotFoundException{
		Optional<Order> opt = orderRepository.findById(id);
		if(opt.isPresent()) {
			Order order = opt.get();
			return order.getMobile();
		} else {
			throw new OrderNotFoundException("No order found with given order id");
		}
	}

	//To calculate total cost
	@Override
	public int calculateTotalCost(int cost) {
		int totalCost, serviceCharge = 100;
		totalCost=cost+(serviceCharge);
		return totalCost;
	}
	
	//To show order details
	@Override
	public Order showOrder(int id) throws OrderNotFoundException {
		Optional<Order> opt=orderRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		} else {
			throw new OrderNotFoundException("No order found with given order id");
		}
	}
	
	//To show orders by a specific customer
	@Override
	public List<Order> showOrderByCustomer(int id) throws CustomerNotFoundException{
		Optional<Customer> opt = customerRepository.findById(id);
		if(opt.isPresent()) {
			return orderRepository.findByCustomer(id);
		} else {
			throw new CustomerNotFoundException("No customer found with given id");
		}
	}
	
	//To show all orders
	@Override
	public List<Order> showAllOrders(){
		return orderRepository.findAll();
	}

}
